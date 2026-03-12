package com.example.skillswap.view.compose.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.skillswap.model.NotificationItem
import com.example.skillswap.ui.theme.*

@Composable
fun NotificationRow(item: NotificationItem) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(SoftBeigeCard),
            contentAlignment = Alignment.Center
        ) {
            Text(text = item.leadingEmoji ?: "🔔", color = TextPrimary)
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {

            val annotated = buildAnnotatedString {
                append(item.title)
                if (!item.highlightText.isNullOrBlank()) {
                    append(" ")
                    withStyle(SpanStyle(color = InfoColor, fontWeight = FontWeight.SemiBold)) {
                        append(item.highlightText)
                    }
                }
            }

            Text(
                text = annotated,
                color = TextPrimary,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            if (item.body.isNotBlank()) {
                Spacer(Modifier.height(4.dp))
                Text(
                    text = item.body,
                    color = TextSecondary,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(Modifier.height(4.dp))
            Text(
                text = item.time,
                color = TextHint,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}