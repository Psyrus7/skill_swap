package com.example.skillswap.view.compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.skillswap.R
import com.example.skillswap.ui.theme.BeigeBackground
import com.example.skillswap.ui.theme.CreamSurface
import com.example.skillswap.ui.theme.SkillSwapTheme

class ChatScreen : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SkillSwapTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {  innerPadding ->
                    MessageScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageScreen() {
    Column(
        modifier = Modifier
            .background(Color(0xFFF2F2F2))
    ) {
        TopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = {}) {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Back Button"
                        )
                    }
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_background),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clip(CircleShape)
                            .size(40.dp)
                    )
                    Column(
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text("Jack king", fontWeight = FontWeight.Bold)
                        Text("Online", style = MaterialTheme.typography.bodySmall)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.videocallicon),
                            contentDescription = "Video Call Icon"
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))
                    TextButton(onClick = {}) {
                        Icon(
                            Icons.Outlined.Call,
                            contentDescription = "Call Icon",
                            modifier = Modifier.absolutePadding(right = 16.dp),
                        )
                    }

                }
            }
        )


        // Messages
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
                .background(CreamSurface)
        ) {
            item {
                MessageBubble(
                    text = "Hi! I'm interested in your property. is it still available",
                    time = "12:43 PM",
                    isUser = true
                )
            }
            item {
                MessageBubble(
                    text = "Yes, it's still available! would you like to schedule a viewing",
                    time = "11:50 AM",
                    isUser = false
                )
            }
            item {
                MessageBubble(
                    text = "Can you meeting with tomorrow?",
                    time = "11:50 AM",
                    isUser = false
                )
            }
            item {
                MessageBubble(
                    text = "Please let me know when you're available.",
                    time = "11:50 AM",
                    isUser = false
                )
            }
            item {
                MessageBubble(
                    text = "Sure",
                    time = "12:43 PM",
                    isUser = true
                )
            }
        }

        // Input bar
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Type a message...") },
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = { /* send action */ }) {
                Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send")
            }
        }
    }
}

@Composable
fun MessageBubble(text: String, time: String, isUser: Boolean) {
    Column(
        horizontalAlignment = if (isUser) Alignment.End else Alignment.Start,
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (isUser) BeigeBackground else Color.White,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp)
        ) {
            Text(text)
        }
        Text(
            text = time,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(4.dp)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SkillSwapTheme {
        MessageScreen()
    }
}