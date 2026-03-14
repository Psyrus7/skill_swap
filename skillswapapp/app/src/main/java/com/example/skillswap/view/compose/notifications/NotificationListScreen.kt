package com.example.skillswap.view.compose.notifications

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.size

import androidx.compose.foundation.layout.width

import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.Button

import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.CenterAlignedTopAppBar

import androidx.compose.material3.Divider

import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.OutlinedButton

import androidx.compose.material3.Scaffold

import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text

import androidx.compose.material3.TopAppBarDefaults

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

import com.example.skillswap.ui.theme.DividerBeige

import com.example.skillswap.ui.theme.SkillSwapTheme

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

    onAccept: (SwapRequestNotification) -> Unit,

    onDecline: (SwapRequestNotification) -> Unit

) {

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(

                title = { Text("Notifications", color = TextPrimary) },

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

                Row(

                    modifier = Modifier

                        .fillMaxWidth()

                        .padding(16.dp),

                    verticalAlignment = Alignment.Top

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

                    Column(modifier = Modifier.weight(1f)) {

                        Text(

                            text = "${request.senderName} sent you a skill swap request",

                            color = TextPrimary,

                            style = MaterialTheme.typography.bodyMedium

                        )

                        Spacer(modifier = Modifier.padding(4.dp))

                        Text(

                            text = "Status: ${request.status}",

                            color = TextHint,

                            style = MaterialTheme.typography.bodySmall

                        )

                        if (request.status == "pending") {

                            Row(

                                modifier = Modifier.padding(top = 8.dp),

                                horizontalArrangement = Arrangement.spacedBy(8.dp)

                            ) {

                                Button(

                                    onClick = { onAccept(request) },

                                    colors = ButtonDefaults.buttonColors(

                                        containerColor = BrownPrimary,

                                        contentColor = Color.White

                                    )

                                ) {

                                    Text("Accept")

                                }

                                OutlinedButton(

                                    onClick = { onDecline(request) }

                                ) {

                                    Text(

                                        text = "Decline",

                                        color = TextSecondary

                                    )

                                }

                            }

                        }

                    }

                }

                Divider(color = DividerBeige)

            }

        }

    }

}
 