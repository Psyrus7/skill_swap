package com.example.skillswap.view.compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.skillswap.R
import com.example.skillswap.model.Conversation
import com.example.skillswap.ui.theme.BeigeBackground
import com.example.skillswap.ui.theme.CreamSurface
import com.example.skillswap.ui.theme.SkillSwapTheme
import com.example.skillswap.ui.theme.TextPrimary
import com.example.skillswap.viewmodel.MessagesViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagesListScreen(navController: NavController, viewModel: MessagesViewModel = viewModel()) {
    var selectedFilter by remember { mutableStateOf("All") }
    val conversations by viewModel.conversations.collectAsState()

    Scaffold(
        bottomBar = {
            SkillSwapBottomBar(navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BeigeBackground)
                .padding(innerPadding)
        ) {

            // Header
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Left side: Back button
                        IconButton(onClick = {
//                            navController.navigate("homeScreen")
                            navController.popBackStack()
                        }) {
                            Icon(
                                Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = "Back Button",
                                tint = TextPrimary
                            )
                        }

                        // Center: Title
                        Text(
                            "Messages",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge
                        )

                        // Right side: Compose icon
                        IconButton(onClick = { /* Compose */ }) {
                            Icon(
                                painterResource(R.drawable.composeicon),
                                contentDescription = "Compose",
                                tint = TextPrimary
                            )
                        }
                    }
                }
            )

            // Search + Filters
            Column(modifier = Modifier.padding(8.dp)) {
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Search", style = MaterialTheme.typography.titleMedium) },
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
                                containerColor = if (isSelected) Color.Black else Color.Transparent,
                                contentColor = if (isSelected) Color.White else Color.Black
                            ),
                            shape = ButtonDefaults.elevatedShape
                        ) {
                            Text(filter, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }

            // Conversation list
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .background(CreamSurface)
            ) {
                items(conversations) { convo ->
                    ConversationItem(convo = convo, onClick = {
                        navController.navigate("chat/${convo.id}")
                    })
                }
            }
        }
    }
}

@Composable
fun ConversationItem(convo: Conversation, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = convo.otherUserProfile,
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
            Text(convo.otherUserName, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
            Text(convo.lastMessage, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(formatTime(convo.timestamp), style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            if (convo.unreadCount > 0) {
                Box(
                    modifier = Modifier
                        .background(Color.Red, CircleShape)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("${convo.unreadCount}", color = Color.White, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}