

package com.example.skillswap.view.compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.skillswap.R
import com.example.skillswap.model.Conversation
import com.example.skillswap.ui.theme.BeigeBackground
import com.example.skillswap.ui.theme.CreamSurface
import com.example.skillswap.ui.theme.SkillSwapTheme
import com.example.skillswap.ui.theme.TextPrimary
import com.example.skillswap.viewmodel.MessagesViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MessageListScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            SkillSwapTheme {

                val navController = rememberNavController()

                MessagesListScreen(navController)

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagesListScreen(
    navController: NavController,
    viewModel: MessagesViewModel = viewModel()
) {

    var selectedFilter by remember { mutableStateOf("All") }

    Scaffold(

        containerColor = BeigeBackground,

        bottomBar = {
            SkillSwapBottomBar(navController = navController)
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            TopAppBar(
                title = {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        IconButton(onClick = { }) {
                            Icon(
                                Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = "Back",
                                tint = TextPrimary
                            )
                        }

                        Text(
                            text = "Messages",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge
                        )

                        IconButton(onClick = { }) {
                            Icon(
                                painterResource(R.drawable.composeicon),
                                contentDescription = "Compose",
                                tint = TextPrimary
                            )
                        }

                    }

                }
            )

            Column(modifier = Modifier.padding(8.dp)) {

                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text("Search")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    listOf("All", "Contacts", "Unknown", "New").forEach { filter ->

                        val isSelected = filter == selectedFilter

                        TextButton(

                            onClick = { selectedFilter = filter },

                            colors = ButtonDefaults.textButtonColors(
                                containerColor =
                                    if (isSelected) Color.Black else Color.Transparent,
                                contentColor =
                                    if (isSelected) Color.White else Color.Black
                            )

                        ) {

                            Text(filter)

                        }

                    }

                }

            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .background(CreamSurface)
            ) {

                items(viewModel.conversations) { convo ->

                    ConversationItem(
                        convo = convo,
                        onClick = { }
                    )

                }

            }

        }

    }

}

@Composable
fun ConversationItem(
    convo: Conversation,
    onClick: () -> Unit
) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(12.dp),

        verticalAlignment = Alignment.CenterVertically

    ) {

        Image(
            painter = painterResource(convo.profileRes),
            contentDescription = "Profile",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {

            Text(
                convo.name,
                fontWeight = FontWeight.Bold
            )

            Text(
                convo.lastMessage,
                color = Color.Gray
            )

        }

        Column(
            horizontalAlignment = Alignment.End
        ) {

            Text(
                convo.time,
                color = Color.Gray
            )

            if (convo.unreadCount > 0) {

                Box(
                    modifier = Modifier
                        .background(Color.Red, CircleShape)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {

                    Text(
                        "${convo.unreadCount}",
                        color = Color.White
                    )

                }

            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun MessagesPreview() {

    SkillSwapTheme {

        val navController = rememberNavController()

        MessagesListScreen(navController)

    }

}
