package com.example.skillswap.view.compose.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.skillswap.model.NotificationItem
import com.example.skillswap.model.NotificationSection
import com.example.skillswap.ui.theme.BeigeBackground
import com.example.skillswap.ui.theme.CreamSurface
import com.example.skillswap.ui.theme.DividerBeige
import com.example.skillswap.ui.theme.SkillSwapTheme
import com.example.skillswap.ui.theme.TextPrimary
import com.example.skillswap.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationListScreen(items: List<NotificationItem>) {
    val today = items.filter { it.section == NotificationSection.Today }
    val yesterday = items.filter { it.section == NotificationSection.Yesterday }
    val weekend = items.filter { it.section == NotificationSection.ThisWeekend }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Notifications", color = TextPrimary) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BeigeBackground,
                    scrolledContainerColor = Color.Unspecified,
                    navigationIconContentColor = Color.Unspecified,
                    titleContentColor = TextPrimary,
                    actionIconContentColor = Color.Unspecified
                )
            )
        },
        containerColor = BeigeBackground
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .background(BeigeBackground)
                .padding(),
            contentPadding = padding
        ) {
            if (today.isNotEmpty()) {
                stickyHeader(key = "header_today") {
                    SectionHeader("Today")
                }
                items(today, key = { it.id }) { item ->
                    NotificationRow(item)
                    Divider(color = DividerBeige)
                }
            }

            // ---- Yesterday ----
            if (yesterday.isNotEmpty()) {
                stickyHeader(key = "header_yesterday") {
                    SectionHeader("Yesterday")
                }
                items(yesterday, key = { it.id }) { item ->
                    NotificationRow(item)
                    Divider(color = DividerBeige)
                }
            }

            // ---- This Weekend ----
            if (weekend.isNotEmpty()) {
                stickyHeader(key = "header_weekend") {
                    SectionHeader("This Weekend")
                }
                items(weekend, key = { it.id }) { item ->
                    NotificationRow(item)
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        color = TextSecondary,
        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
        modifier = Modifier
            .background(CreamSurface)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewListScrollable() {
    SkillSwapTheme { NotificationListScreen(items = sampleNotifications()) }
}