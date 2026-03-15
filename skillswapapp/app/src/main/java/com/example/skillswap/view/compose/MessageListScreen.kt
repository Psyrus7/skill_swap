
package com.example.skillswap.view.compose

import android.net.Uri

import androidx.compose.foundation.background

import androidx.compose.foundation.border

import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.CircleShape

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavController

import coil.compose.AsyncImage

import com.example.skillswap.model.Conversation

import com.example.skillswap.ui.theme.*

import com.example.skillswap.viewmodel.MessagesViewModel

@OptIn(ExperimentalMaterial3Api::class)

@Composable

fun MessagesListScreen(

    navController: NavController,

    viewModel: MessagesViewModel = viewModel()

) {

    val conversations by viewModel.conversations.collectAsState()

    var search by remember { mutableStateOf("") }

    val filteredConversations = conversations.filter {

        it.otherUserName.contains(search, ignoreCase = true)

    }

    Scaffold(

        containerColor = BeigeBackground,

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

            CenterAlignedTopAppBar(

                title = {

                    Text(

                        text = "Messages",

                        fontWeight = FontWeight.Bold,

                        style = MaterialTheme.typography.titleLarge,

                        color = TitleText

                    )

                },

                navigationIcon = {

                    IconButton(

                        onClick = { navController.popBackStack() }

                    ) {

                        Icon(

                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,

                            contentDescription = "Back Button",

                            tint = TitleText

                        )

                    }

                },

                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(

                    containerColor = CreamSurface

                )

            )

            /* ---------------- SEARCH BAR ---------------- */

            Column(

                modifier = Modifier

                    .padding(12.dp)

                    .background(BeigeBackground)

            ) {

                OutlinedTextField(

                    value = search,

                    onValueChange = { search = it },

                    placeholder = {

                        Text(

                            text = "Search conversations...",

                            color = TextHint

                        )

                    },

                    modifier = Modifier.fillMaxWidth(),

                    singleLine = true,

                    shape = RoundedCornerShape(16.dp),

                    colors = OutlinedTextFieldDefaults.colors(

                        focusedContainerColor = SearchBackground,

                        unfocusedContainerColor = SearchBackground,

                        focusedBorderColor = SearchBorder,

                        unfocusedBorderColor = SearchBorder,

                        focusedTextColor = TextPrimary,

                        unfocusedTextColor = TextPrimary,

                        cursorColor = BrownPrimary

                    )

                )

            }

            /* ---------------- EMPTY STATE ---------------- */

            if (filteredConversations.isEmpty()) {

                Box(

                    modifier = Modifier

                        .fillMaxSize()

                        .background(BeigeBackground),

                    contentAlignment = Alignment.Center

                ) {

                    Text(

                        text = "No messages yet",

                        color = TextHint,

                        style = MaterialTheme.typography.bodyLarge

                    )

                }

            } else {

                /* ---------------- CONVERSATION LIST ---------------- */

                LazyColumn(

                    modifier = Modifier

                        .fillMaxSize()

                        .background(BeigeBackground),

                    verticalArrangement = Arrangement.spacedBy(10.dp)

                ) {

                    items(filteredConversations) { convo ->

                        ConversationItem(

                            convo = convo,

                            onClick = {

                                navController.navigate(

                                    "chat/${convo.id}/${Uri.encode(convo.otherUserName)}"

                                )

                            }

                        )

                    }

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

            .padding(horizontal = 12.dp)

            .background(

                color = SoftBeigeCard,

                shape = RoundedCornerShape(18.dp)

            )

            .border(

                width = 1.dp,

                color = DividerBeige,

                shape = RoundedCornerShape(18.dp)

            )

            .clickable(onClick = onClick)

            .padding(14.dp),

        verticalAlignment = Alignment.CenterVertically

    ) {

        /* -------- PROFILE IMAGE -------- */

        AsyncImage(

            model = convo.otherUserProfile,

            contentDescription = "Profile",

            modifier = Modifier

                .size(52.dp)

                .clip(CircleShape)

                .background(CreamSurface)

        )

        /* -------- NAME + LAST MESSAGE -------- */

        Column(

            modifier = Modifier

                .weight(1f)

                .padding(start = 12.dp)

        ) {

            Text(

                text = convo.otherUserName,

                fontWeight = FontWeight.Bold,

                style = MaterialTheme.typography.bodyLarge,

                color = TextPrimary

            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(

                text = convo.lastMessage,

                style = MaterialTheme.typography.bodySmall,

                color = TextSecondary,

                maxLines = 1

            )

        }

        /* -------- TIME + UNREAD -------- */

        Column(

            horizontalAlignment = Alignment.End

        ) {

            Text(

                text = formatTime(convo.timestamp),

                style = MaterialTheme.typography.bodySmall,

                color = TextHint

            )

            if (convo.unreadCount > 0) {

                Spacer(modifier = Modifier.height(6.dp))

                Box(

                    modifier = Modifier

                        .background(BrownPrimary, CircleShape)

                        .padding(horizontal = 8.dp, vertical = 4.dp)

                ) {

                    Text(

                        text = "${convo.unreadCount}",

                        color = Color.White,

                        style = MaterialTheme.typography.bodySmall

                    )

                }

            }

        }

    }

}



























//package com.example.skillswap.view.compose
//
//import android.net.Uri
//
//import androidx.compose.foundation.background
//
//import androidx.compose.foundation.border
//
//import androidx.compose.foundation.clickable
//
//import androidx.compose.foundation.layout.Arrangement
//
//import androidx.compose.foundation.layout.Box
//
//import androidx.compose.foundation.layout.Column
//
//import androidx.compose.foundation.layout.Row
//
//import androidx.compose.foundation.layout.fillMaxSize
//
//import androidx.compose.foundation.layout.fillMaxWidth
//
//import androidx.compose.foundation.layout.padding
//
//import androidx.compose.foundation.layout.size
//
//import androidx.compose.foundation.layout.Spacer
//
//import androidx.compose.foundation.layout.width
//
//import androidx.compose.foundation.layout.height
//
//import androidx.compose.foundation.lazy.LazyColumn
//
//import androidx.compose.foundation.lazy.items
//
//import androidx.compose.foundation.shape.CircleShape
//
//import androidx.compose.foundation.shape.RoundedCornerShape
//
//import androidx.compose.material.icons.Icons
//
//import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
//
//import androidx.compose.material3.ExperimentalMaterial3Api
//
//import androidx.compose.material3.Icon
//
//import androidx.compose.material3.IconButton
//
//import androidx.compose.material3.MaterialTheme
//
//import androidx.compose.material3.OutlinedTextField
//
//import androidx.compose.material3.OutlinedTextFieldDefaults
//
//import androidx.compose.material3.Scaffold
//
//import androidx.compose.material3.Text
//
//import androidx.compose.material3.TopAppBar
//
//import androidx.compose.runtime.Composable
//
//import androidx.compose.runtime.collectAsState
//
//import androidx.compose.runtime.getValue
//
//import androidx.compose.runtime.mutableStateOf
//
//import androidx.compose.runtime.remember
//
//import androidx.compose.runtime.setValue
//
//import androidx.compose.ui.Alignment
//
//import androidx.compose.ui.Modifier
//
//import androidx.compose.ui.draw.clip
//
//import androidx.compose.ui.graphics.Color
//
//import androidx.compose.ui.res.painterResource
//
//import androidx.compose.ui.text.font.FontWeight
//
//import androidx.compose.ui.unit.dp
//
//import androidx.lifecycle.viewmodel.compose.viewModel
//
//import androidx.navigation.NavController
//
//import coil.compose.AsyncImage
//
//import com.example.skillswap.R
//
//import com.example.skillswap.model.Conversation
//
//import com.example.skillswap.ui.theme.BeigeBackground
//
//import com.example.skillswap.ui.theme.BrownPrimary
//
//import com.example.skillswap.ui.theme.CreamSurface
//
//import com.example.skillswap.ui.theme.DividerBeige
//
//import com.example.skillswap.ui.theme.SearchBackground
//
//import com.example.skillswap.ui.theme.SearchBorder
//
//import com.example.skillswap.ui.theme.SoftBeigeCard
//
//import com.example.skillswap.ui.theme.TextHint
//
//import com.example.skillswap.ui.theme.TextPrimary
//
//import com.example.skillswap.ui.theme.TextSecondary
//
//import com.example.skillswap.ui.theme.TitleText
//
//import com.example.skillswap.viewmodel.MessagesViewModel
//
//@OptIn(ExperimentalMaterial3Api::class)
//
//@Composable
//
//fun MessagesListScreen(
//
//    navController: NavController,
//
//    viewModel: MessagesViewModel = viewModel()
//
//) {
//
//    val conversations by viewModel.conversations.collectAsState()
//
//    var search by remember { mutableStateOf("") }
//
//    val filteredConversations = conversations.filter {
//
//        it.otherUserName.contains(search, ignoreCase = true)
//
//    }
//
//    Scaffold(
//
//        containerColor = BeigeBackground,
//
//        bottomBar = {
//
//            SkillSwapBottomBar(navController)
//
//        }
//
//    ) { innerPadding ->
//
//        Column(
//
//            modifier = Modifier
//
//                .fillMaxSize()
//
//                .background(BeigeBackground)
//
//                .padding(innerPadding)
//
//        ) {
//
//            TopAppBar(
//
//                title = {
//
//                    Row(
//
//                        modifier = Modifier.fillMaxWidth(),
//
//                        verticalAlignment = Alignment.CenterVertically,
//
//                        horizontalArrangement = Arrangement.SpaceBetween
//
//                    ) {
//
//                        IconButton(
//
//                            onClick = { navController.popBackStack() }
//
//                        ) {
//
//                            Icon(
//
//                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
//
//                                contentDescription = "Back Button",
//
//                                tint = TitleText
//
//                            )
//
//                        }
//
//                        Text(
//
//                            text = "Messages",
//
//                            fontWeight = FontWeight.Bold,
//
//                            style = MaterialTheme.typography.titleLarge,
//
//                            color = TitleText
//
//                        )
//
//
//                    }
//
//                },
//
//                modifier = Modifier.border(0.dp, Color.Transparent),
//
//                colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
//
//                    containerColor = CreamSurface
//
//                )
//
//            )
//
//            Column(
//
//                modifier = Modifier
//
//                    .padding(12.dp)
//
//                    .background(BeigeBackground)
//
//            ) {
//
//                OutlinedTextField(
//
//                    value = search,
//
//                    onValueChange = { search = it },
//
//                    placeholder = {
//
//                        Text(
//
//                            text = "Search conversations...",
//
//                            color = TextHint
//
//                        )
//
//                    },
//
//                    modifier = Modifier.fillMaxWidth(),
//
//                    singleLine = true,
//
//                    shape = RoundedCornerShape(16.dp),
//
//                    colors = OutlinedTextFieldDefaults.colors(
//
//                        focusedContainerColor = SearchBackground,
//
//                        unfocusedContainerColor = SearchBackground,
//
//                        focusedBorderColor = SearchBorder,
//
//                        unfocusedBorderColor = SearchBorder,
//
//                        focusedTextColor = TextPrimary,
//
//                        unfocusedTextColor = TextPrimary,
//
//                        cursorColor = BrownPrimary
//
//                    )
//
//                )
//
//            }
//
//            if (filteredConversations.isEmpty()) {
//
//                Box(
//
//                    modifier = Modifier
//
//                        .fillMaxSize()
//
//                        .background(BeigeBackground),
//
//                    contentAlignment = Alignment.Center
//
//                ) {
//
//                    Text(
//
//                        text = "No messages yet",
//
//                        color = TextHint,
//
//                        style = MaterialTheme.typography.bodyLarge
//
//                    )
//
//                }
//
//            } else {
//
//                LazyColumn(
//
//                    modifier = Modifier
//
//                        .fillMaxSize()
//
//                        .background(BeigeBackground),
//
//                    verticalArrangement = Arrangement.spacedBy(10.dp)
//
//                ) {
//
//                    items(filteredConversations) { convo ->
//
//                        ConversationItem(
//
//                            convo = convo,
//
//                            onClick = {
//
//                                navController.navigate(
//
//                                    "chat/${convo.id}/${Uri.encode(convo.otherUserName)}"
//
//                                )
//
//                            }
//
//                        )
//
//                    }
//
//                }
//
//            }
//
//        }
//
//    }
//
//}
//
//@Composable
//
//fun ConversationItem(
//
//    convo: Conversation,
//
//    onClick: () -> Unit
//
//) {
//
//    Row(
//
//        modifier = Modifier
//
//            .fillMaxWidth()
//
//            .padding(horizontal = 12.dp)
//
//            .background(
//
//                color = SoftBeigeCard,
//
//                shape = RoundedCornerShape(18.dp)
//
//            )
//
//            .border(
//
//                width = 1.dp,
//
//                color = DividerBeige,
//
//                shape = RoundedCornerShape(18.dp)
//
//            )
//
//            .clickable(onClick = onClick)
//
//            .padding(14.dp),
//
//        verticalAlignment = Alignment.CenterVertically
//
//    ) {
//
//        AsyncImage(
//
//            model = convo.otherUserProfile,
//
//            contentDescription = "Profile",
//
//            modifier = Modifier
//
//                .size(52.dp)
//
//                .clip(CircleShape)
//
//                .background(CreamSurface)
//
//        )
//
//        Column(
//
//            modifier = Modifier
//
//                .weight(1f)
//
//                .padding(start = 12.dp)
//
//        ) {
//
//            Text(
//
//                text = convo.otherUserName,
//
//                fontWeight = FontWeight.Bold,
//
//                style = MaterialTheme.typography.bodyLarge,
//
//                color = TextPrimary
//
//            )
//
//            Spacer(modifier = Modifier.height(4.dp))
//
//            Text(
//
//                text = convo.lastMessage,
//
//                style = MaterialTheme.typography.bodySmall,
//
//                color = TextSecondary,
//
//                maxLines = 1
//
//            )
//
//        }
//
//        Column(
//
//            horizontalAlignment = Alignment.End
//
//        ) {
//
//            Text(
//
//                text = formatTime(convo.timestamp),
//
//                style = MaterialTheme.typography.bodySmall,
//
//                color = TextHint
//
//            )
//
//            if (convo.unreadCount > 0) {
//
//                Spacer(modifier = Modifier.height(6.dp))
//
//                Box(
//
//                    modifier = Modifier
//
//                        .background(BrownPrimary, CircleShape)
//
//                        .padding(horizontal = 8.dp, vertical = 4.dp)
//
//                ) {
//
//                    Text(
//
//                        text = "${convo.unreadCount}",
//
//                        color = Color.White,
//
//                        style = MaterialTheme.typography.bodySmall
//
//                    )
//
//                }
//
//            }
//
//        }
//
//    }
//
//}
