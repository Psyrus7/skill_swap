package com.example.skillswap.view.compose.notifications

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.size

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button

import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.CenterAlignedTopAppBar

import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Scaffold

import androidx.compose.material3.Surface

import androidx.compose.material3.Text

import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.runtime.Composable

import androidx.navigation.NavController

import androidx.navigation.compose.rememberNavController

import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp

import com.example.skillswap.ui.theme.BeigeBackground

import com.example.skillswap.ui.theme.ButtonPrimary

import com.example.skillswap.ui.theme.CreamSurface

import com.example.skillswap.ui.theme.SkillSwapTheme

import com.example.skillswap.ui.theme.TextHint

import com.example.skillswap.ui.theme.TextPrimary

import com.example.skillswap.ui.theme.TextSecondary

import com.example.skillswap.view.compose.SkillSwapBottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmptyNotificationsScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Notifications",
                        color = TextPrimary
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BeigeBackground,
                    titleContentColor = TextPrimary
                )
            )
        },
        bottomBar = {
            SkillSwapBottomBar(navController = navController)
        },
        containerColor = BeigeBackground
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            /* ---------- Empty Illustration Box ---------- */
            Surface(
                modifier = Modifier.size(140.dp),
                color = CreamSurface,
                shape = RoundedCornerShape(24.dp)
            ) {}
            Spacer(modifier = Modifier.height(24.dp))
            /* ---------- Title ---------- */
            Text(
                text = "No Notification to show",
                color = TextSecondary,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            /* ---------- Description ---------- */
            Text(
                text = "You currently have no notifications. We will notify you when something new happens!",
                color = TextHint,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            /* ---------- Explore Button ---------- */
            Button(
                onClick = {
                    navController.navigate("homeScreen") {
                        popUpTo("notificationScreen") { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonPrimary,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Explore")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewEmptyNotifications() {
    val navController = rememberNavController()
    SkillSwapTheme {
        EmptyNotificationsScreen(navController)
    }
}
 