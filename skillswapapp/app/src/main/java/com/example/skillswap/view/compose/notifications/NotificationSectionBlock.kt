package com.example.skillswap.view.compose.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.skillswap.model.NotificationItem
import com.example.skillswap.model.NotificationSection
import com.example.skillswap.ui.theme.*

@Composable
fun NotificationSectionBlock(
    section: NotificationSection,
    items: List<NotificationItem>
) {
    if (items.isEmpty()) return

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CreamSurface)
    ) {

        Text(
            text = when (section) {
                NotificationSection.Today -> "Today"
                NotificationSection.Yesterday -> "Yesterday"
                NotificationSection.ThisWeekend -> "This Weekend"
            },
            color = TextSecondary,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        )

        items.forEachIndexed { index, item ->
            NotificationRow(item)
            if (index != items.lastIndex) {
                Divider(color = DividerBeige)
            }
        }
    }
}