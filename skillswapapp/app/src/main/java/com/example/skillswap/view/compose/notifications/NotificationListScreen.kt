package com.example.skillswap.view.compose.notifications

import androidx.compose.foundation.background

import androidx.compose.foundation.border

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.CircleShape

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material3.*

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

import com.example.skillswap.model.SwapRequestNotification

import com.example.skillswap.ui.theme.BeigeBackground

import com.example.skillswap.ui.theme.BrownPrimary

import com.example.skillswap.ui.theme.CreamSurface

import com.example.skillswap.ui.theme.DividerBeige

import com.example.skillswap.ui.theme.SoftBeigeCard

import com.example.skillswap.ui.theme.TextHint

import com.example.skillswap.ui.theme.TextPrimary

import com.example.skillswap.ui.theme.TextSecondary

import com.example.skillswap.view.compose.SkillSwapBottomBar

@OptIn(ExperimentalMaterial3Api::class)

@Composable

fun NotificationsListScreen(

    navController: NavController,

    requests: List<SwapRequestNotification>,

    currentUserId: String,

    onAccept: (SwapRequestNotification) -> Unit,

    onDecline: (SwapRequestNotification) -> Unit

) {

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(

                title = { Text("Notifications", color = TextPrimary) },

                navigationIcon = {

                    IconButton(

                        onClick = {

                            navController.navigate("homeScreen") {

                                popUpTo("notificationScreen") { inclusive = true }

                            }

                        }

                    ) {

                        Icon(

                            imageVector = Icons.Default.ArrowBack,

                            contentDescription = "Back",

                            tint = TextPrimary

                        )

                    }

                },

                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(

                    containerColor = BeigeBackground

                )

            )

        },

        bottomBar = {

            SkillSwapBottomBar(navController = navController)

        },

        containerColor = BeigeBackground

    ) { padding ->

        LazyColumn(

            modifier = Modifier

                .fillMaxSize()

                .padding(padding)

        ) {

            items(requests) { request ->

                Column(

                    modifier = Modifier

                        .fillMaxWidth()

                        .padding(horizontal = 16.dp, vertical = 8.dp)

                        .background(CreamSurface, shape = RoundedCornerShape(16.dp))

                        .border(1.dp, DividerBeige, RoundedCornerShape(16.dp))

                        .padding(16.dp)

                ) {

                    Row(

                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Box(

                            modifier = Modifier

                                .size(40.dp)

                                .clip(CircleShape)

                                .background(SoftBeigeCard),

                            contentAlignment = Alignment.Center

                        ) {

                            Text("🔔")

                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(

                            modifier = Modifier.weight(1f)

                        ) {

                            val message = when {

                                request.receiverId == currentUserId &&

                                        request.status == "pending" ->

                                    "${request.senderName} sent you a skill swap request"

                                request.senderId == currentUserId &&

                                        request.status == "accepted" ->

                                    "${request.receiverName} accepted your skill swap request"

                                else ->

                                    "Skill swap update"

                            }

                            Text(

                                text = message,

                                color = TextPrimary,

                                style = MaterialTheme.typography.bodyMedium

                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(

                                text = "Status: ${request.status}",

                                color = TextHint,

                                style = MaterialTheme.typography.bodySmall

                            )

                        }

                    }

                    if (request.status == "pending") {

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(

                            horizontalArrangement = Arrangement.spacedBy(10.dp)

                        ) {

                            Button(

                                onClick = {

                                    onAccept(request)

                                    navController.navigate("homeScreen") {

                                        popUpTo("homeScreen") { inclusive = false }

                                    }

                                },

                                colors = ButtonDefaults.buttonColors(

                                    containerColor = BrownPrimary,

                                    contentColor = Color.White

                                ),

                                shape = RoundedCornerShape(12.dp)

                            ) {

                                Text("Accept")

                            }

                            OutlinedButton(

                                onClick = { onDecline(request) },

                                shape = RoundedCornerShape(12.dp)

                            ) {

                                Text(

                                    text = "Decline",

                                    color = TextSecondary

                                )

                            }

                        }

                    }

                }

                Divider(color = DividerBeige)

            }

        }

    }

}
 