package com.example.skillswap.view.compose

import android.annotation.SuppressLint

import android.content.Intent

import android.net.Uri

import android.os.Bundle

import androidx.activity.ComponentActivity

import androidx.activity.compose.setContent

import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.Image

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.CircleShape

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft

import androidx.compose.material.icons.automirrored.filled.Send

import androidx.compose.material.icons.outlined.Call

import androidx.compose.material3.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.collectAsState

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavController

import com.example.skillswap.R

import com.example.skillswap.ui.theme.BeigeBackground

import com.example.skillswap.ui.theme.SkillSwapTheme

import com.example.skillswap.ui.theme.SoftBeigeCard

import com.example.skillswap.viewmodel.ChatViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatTime(date: Date?): String {
    if(date==null) return ""
    val sdf = SimpleDateFormat("hh:mm a" , Locale.getDefault())
    return sdf.format(date)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MessageScreen(

    navController: NavController,
    conversationId: String,
    userName: String,
    viewModel: ChatViewModel = viewModel()

) {

    val messages by viewModel.messages.collectAsState()
    val context = LocalContext.current
    var messageText by remember { mutableStateOf("") }
    LaunchedEffect(conversationId) {
        viewModel.listenMessages(conversationId)
    }
    Scaffold(
        containerColor = BeigeBackground,
        bottomBar = {
            SkillSwapBottomBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = "Back"
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
                            modifier = Modifier.padding(start = 12.dp)
                        ) {
                            Text(
                                userName,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text("Online", style = MaterialTheme.typography.bodySmall)
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(
                            onClick = {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://meet.google.com/new")
                                )
                                context.startActivity(intent)
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.videocallicon),
                                contentDescription = "Video Call"
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        TextButton(onClick = {}) {
                            Icon(
                                Icons.Outlined.Call,
                                contentDescription = "Call",
                                )
                        }
                    }
                }
            )
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .background(BeigeBackground)
            ) {
                items(messages) { message ->
                    val isUser = message.senderId==viewModel.currentUserId
                    MessageBubble(
                        text = message.text,
                        time = formatTime(message.timestamp),
                        isUser = isUser
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = messageText,
                    onValueChange = {messageText=it},
                    placeholder = { Text("Type a message...") },
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = {
                    viewModel.sendMessage(
                        text = messageText,
                        conversationId = conversationId,
                        otherUserName = userName )
                    messageText=""
                }) {
                    Icon(
                        Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Send"
                    )
                }
            }
        }
    }
}

@Composable
fun MessageBubble(text: String, time: String, isUser: Boolean) {
    Column(
        horizontalAlignment = if (isUser) Alignment.End else Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (isUser) SoftBeigeCard else Color.White,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp)
        ) {
            Text(text, style = MaterialTheme.typography.bodyMedium)
        }
        Text(
            text = time,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(4.dp)
        )
    }
}

