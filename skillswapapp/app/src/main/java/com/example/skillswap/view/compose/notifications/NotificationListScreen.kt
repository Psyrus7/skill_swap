package com.example.skillswap.view.compose.notifications

import androidx.compose.foundation.background
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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.skillswap.model.NotificationItem
import com.example.skillswap.ui.theme.BeigeBackground
import com.example.skillswap.ui.theme.DividerBeige
import com.example.skillswap.ui.theme.SkillSwapTheme
import com.example.skillswap.ui.theme.SoftBeigeCard
import com.example.skillswap.ui.theme.TextHint
import com.example.skillswap.ui.theme.TextPrimary
import com.example.skillswap.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsListScreen(items: List<NotificationItem>) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Notifications", color = TextPrimary) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BeigeBackground
                )
            )
        },
        containerColor = BeigeBackground
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            items(items) { item ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(SoftBeigeCard),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(item.leadingEmoji ?: "🔔")
                    }

                    Spacer(Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {

                        Text(
                            text = item.title,
                            color = TextPrimary,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        if (item.body.isNotBlank()) {
                            Text(
                                text = item.body,
                                color = TextSecondary,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        Text(
                            text = item.time,
                            color = TextHint,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }

                Divider(color = DividerBeige)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewListScrollable() {
    SkillSwapTheme { NotificationsListScreen(sampleNotifications()) }
}
