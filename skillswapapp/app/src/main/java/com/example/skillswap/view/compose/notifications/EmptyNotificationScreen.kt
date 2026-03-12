package com.example.skillswap.view.compose.notifications

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.skillswap.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmptyNotificationsScreen(onExploreClick: () -> Unit = {}) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Notifications", color = TextPrimary) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BeigeBackground,
                    titleContentColor = TextPrimary
                )
            )
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
            Surface(
                modifier = Modifier.size(140.dp),
                color = CreamSurface,
                shape = RoundedCornerShape(24.dp)
            ) {}

            Spacer(Modifier.height(24.dp))

            Text(
                "No Notification to show",
                color = TextSecondary,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(8.dp))

            Text(
                "You currently have no notifications. We will notify you when something new happens!",
                color = TextHint,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = onExploreClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonPrimary,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) { Text("Explore") }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewEmpty() {
    SkillSwapTheme { EmptyNotificationsScreen() }
}