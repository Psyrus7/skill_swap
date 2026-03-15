package com.example.skillswap.view.compose

import android.os.Bundle

import androidx.activity.ComponentActivity

import androidx.activity.compose.setContent

import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.wrapContentHeight

import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.width

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button

import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.DividerDefaults

import androidx.compose.material3.HorizontalDivider

import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Scaffold

import androidx.compose.material3.Surface

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

import com.example.skillswap.model.SwapRequestNotification

import com.example.skillswap.ui.theme.BeigeBackground

import com.example.skillswap.ui.theme.ButtonPrimary

import com.example.skillswap.ui.theme.ButtonSecondary

import com.example.skillswap.ui.theme.DividerBeige

import com.example.skillswap.ui.theme.SkillSwapTheme

import com.example.skillswap.ui.theme.SoftBeigeCard

import com.example.skillswap.ui.theme.TextPrimary

import com.example.skillswap.ui.theme.TextSecondary

import com.example.skillswap.ui.theme.TitleText

import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.FirebaseDatabase

class MatchedScreenCardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(
                    containerColor = BeigeBackground
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                    }
                }
            }
        }
    }
}

@Composable

fun MatchedScreenCard(
    navController: NavController,
    name: String = "Sample Name",
    receiverId: String,
    onSendRequest: () -> Unit = {},
    onMessage: () -> Unit = {
        navController.navigate("chatScreen")
    }

) {
    var requestStatus by rememberSaveable { mutableStateOf("none") }
    LaunchedEffect(receiverId) {
        val currentUser = FirebaseAuth.getInstance().currentUser ?: return@LaunchedEffect
        val db = FirebaseDatabase.getInstance().getReference("requests")
        db.orderByChild("senderId")
            .equalTo(currentUser.uid)
            .addValueEventListener(object : com.google.firebase.database.ValueEventListener {
                override fun onDataChange(snapshot: com.google.firebase.database.DataSnapshot) {
                    requestStatus = "none"
                    for (child in snapshot.children) {
                        val receiver = child.child("receiverId").value?.toString()
                        if (receiver == receiverId) {
                            requestStatus = child.child("status").value?.toString() ?: "pending"
                            break
                        }
                    }
                }
                override fun onCancelled(error: com.google.firebase.database.DatabaseError) {
                }
            })
    }

    Box(

        modifier = Modifier.padding(16.dp),

        contentAlignment = Alignment.Center

    ) {

        Surface(

            color = SoftBeigeCard,

            shape = RoundedCornerShape(20.dp),

            tonalElevation = 2.dp,

            modifier = Modifier

                .fillMaxWidth()

                .wrapContentHeight()

        ) {

            Column(

                modifier = Modifier.padding(20.dp),

                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Text(

                    text = "Skill Matched",

                    color = TitleText,

                    style = MaterialTheme.typography.headlineSmall,

                    fontWeight = FontWeight.Bold

                )

                Spacer(Modifier.height(12.dp))

                HorizontalDivider(Modifier, DividerDefaults.Thickness, color = DividerBeige)

                Spacer(Modifier.height(12.dp))

                Text(

                    text = "Wanna Connect\nwith $name ?",

                    color = TextPrimary,

                    style = MaterialTheme.typography.headlineMedium,

                    textAlign = TextAlign.Center

                )

                Spacer(Modifier.height(16.dp))

                Text(

                    text = "You want to learn acting\n$name wants to learn coding",

                    color = TextSecondary,

                    style = MaterialTheme.typography.bodyMedium,

                    textAlign = TextAlign.Center

                )

                Spacer(Modifier.height(20.dp))
                Button(

                    onClick = {

                        val currentUser = FirebaseAuth.getInstance().currentUser ?: return@Button

                        val requestRef = FirebaseDatabase.getInstance()

                            .getReference("requests")

                            .push()

                        val request = SwapRequestNotification(

                            id = requestRef.key ?: "",

                            senderId = currentUser.uid,

                            senderName = currentUser.email ?: "Unknown User",

                            receiverId = receiverId,

                            receiverName = name,

                            status = "pending"

                        )

                        requestRef.setValue(request)

                            .addOnSuccessListener {

                                requestStatus = "pending"

                                onSendRequest()

                            }

                    },

                    enabled = requestStatus == "none",

                    modifier = Modifier.width(240.dp),

                    colors = ButtonDefaults.buttonColors(

                        containerColor = ButtonPrimary,

                        contentColor = Color.White

                    )

                ) {

                    Text(
                        when(requestStatus){
                            "accepted" -> "Message"
                            "pending" -> "Waiting for approval"
                            else -> "Send Request"
                        })


                }


                Spacer(Modifier.height(12.dp))

                Button(

                    onClick = onMessage,

                    enabled = requestStatus == "accepted",

                    modifier = Modifier.width(240.dp),

                    colors = ButtonDefaults.buttonColors(

                        containerColor = ButtonSecondary,

                        contentColor = TextPrimary,

                        disabledContainerColor = ButtonSecondary.copy(alpha = 0.5f),

                        disabledContentColor = TextPrimary.copy(alpha = 0.5f)

                    )

                ) {

                    Text(

                        when (requestStatus) {

                            "accepted" -> "Message"

                            "pending" -> "Waiting for approval"

                            else -> "Message Locked"

                        }

                    )

                }




            }

        }

    }

}
