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

import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.width

import androidx.compose.foundation.layout.wrapContentHeight

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button

import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider

import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Scaffold

import androidx.compose.material3.Surface

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import com.example.skillswap.ui.theme.BeigeBackground
import com.example.skillswap.ui.theme.ButtonPrimary
import com.example.skillswap.ui.theme.ButtonSecondary
import com.example.skillswap.ui.theme.DividerBeige

import com.example.skillswap.ui.theme.SkillSwapTheme
import com.example.skillswap.ui.theme.SoftBeigeCard
import com.example.skillswap.ui.theme.TextPrimary
import com.example.skillswap.ui.theme.TextSecondary
import com.example.skillswap.ui.theme.TitleText

class MatchedScreenCard : ComponentActivity() {

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

                        MatchedScreenCard(

                            name = "Ana de Armas",

                            onSendRequest = { /* TODO */ },

                            onMessage = { /* TODO */ }

                        )

                    }

                }

            }

        }

    }

}

@Composable

fun MatchedScreenCard(

    name: String = "Sample Name",

    onSendRequest: () -> Unit = {},

    onMessage: () -> Unit = {}

) {

    Box(

        modifier = Modifier
            .padding(16.dp),

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

                    onClick = onSendRequest,

                    modifier = Modifier.width(240.dp),

                    colors = ButtonDefaults.buttonColors(

                        containerColor = ButtonPrimary,

                        contentColor = Color.White

                    )

                ) {

                    Text("Send Request")

                }

                Spacer(Modifier.height(12.dp))

                Button(

                    onClick = onMessage,

                    modifier = Modifier.width(240.dp),

                    colors = ButtonDefaults.buttonColors(

                        containerColor = ButtonSecondary,

                        contentColor = TextPrimary

                    )

                ) {

                    Text("Message")

                }

            }

        }

    }

}

@Preview(showBackground = true)

@Composable

private fun PreviewSkillMatchedCardSimple() {

    MaterialTheme {

        MatchedScreenCard("Android")

    }

}