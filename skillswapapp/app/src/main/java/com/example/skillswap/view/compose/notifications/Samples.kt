package com.example.skillswap.view.compose.notifications

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.skillswap.model.NotificationItem
import com.example.skillswap.model.NotificationSection
import com.example.skillswap.ui.theme.SkillSwapTheme



fun sampleNotifications(): List<NotificationItem> = listOf(
    // TODAY
    NotificationItem(
        id = "n1",
        title = "Profile set: You know",
        body = "and you want to learn Coding. Start swiping to find a match.",
        time = "12m ago",
        leadingEmoji = "🎸",
        highlightText = "Guitar",
        section = NotificationSection.Today
    ),
    NotificationItem(
        id = "n2",
        title = "New match found for",
        body = "Tap to see details and start a swap.",
        time = "9:48 AM",
        leadingEmoji = "🤝",
        highlightText = "Guitar ↔ Coding",
        section = NotificationSection.Today
    ),
    NotificationItem(
        id = "n3",
        title = "Swap request sent to",
        body = "We’ll notify you when they respond.",
        time = "9:22 AM",
        leadingEmoji = "📤",
        highlightText = "Rahul (Coding)",
        section = NotificationSection.Today
    ),
    NotificationItem(
        id = "n4",
        title = "Swap request accepted by",
        body = "Schedule your first session and discuss goals.",
        time = "8:55 AM",
        leadingEmoji = "✅",
        highlightText = "Anu (Coding)",
        section = NotificationSection.Today
    ),
    NotificationItem(
        id = "n5",
        title = "New chat message from",
        body = "“Hey! Excited to learn Guitar, when are you free?”",
        time = "8:40 AM",
        leadingEmoji = "💬",
        highlightText = "Karan",
        section = NotificationSection.Today
    ),
    // YESTERDAY
    NotificationItem(
        id = "n6",
        title = "Reminder:",
        body = "Your Guitar ↔ Coding trial session starts today at 7:30 PM.",
        time = "Yesterday • 4:10 PM",
        leadingEmoji = "⏰",
        highlightText = "Session at 7:30 PM",
        section = NotificationSection.Yesterday
    ),
    NotificationItem(
        id = "n7",
        title = "Feedback pending for",
        body = "Help the community by rating your last swap partner.",
        time = "Yesterday • 2:03 PM",
        leadingEmoji = "⭐",
        highlightText = "Riya (Coding)",
        section = NotificationSection.Yesterday
    ),
    // THIS WEEKEND
    NotificationItem(
        id = "n8",
        title = "Weekend spotlight:",
        body = "Top learners near you are looking for Guitar mentors.",
        time = "Sat • 11:00 AM",
        leadingEmoji = "🎯",
        highlightText = "Guitar mentors",
        section = NotificationSection.ThisWeekend
    ),
    NotificationItem(
        id = "n9",
        title = "Community tip:",
        body = "Share a 30‑sec riff to attract Coding learners who love music.",
        time = "Sat • 10:05 AM",
        leadingEmoji = "🎥",
        highlightText = "Video intro",
        section = NotificationSection.ThisWeekend
    ),
    NotificationItem(
        id = "n10",
        title = "Community tip:",
        body = "Share a 30‑sec riff to attract Coding learners who love music.",
        time = "Sat • 10:05 AM",
        leadingEmoji = "🎥",
        highlightText = "Video intro",
        section = NotificationSection.ThisWeekend
    ),
    NotificationItem(
        id = "n11",
        title = "Community tip:",
        body = "Share a 30‑sec riff to attract Coding learners who love music.",
        time = "Sat • 10:05 AM",
        leadingEmoji = "🎥",
        highlightText = "Video intro",
        section = NotificationSection.ThisWeekend
    ),
    NotificationItem(
        id = "n12",
        title = "Community tip:",
        body = "Share a 30‑sec riff to attract Coding learners who love music.",
        time = "Sat • 10:05 AM",
        leadingEmoji = "🎥",
        highlightText = "Video intro",
        section = NotificationSection.ThisWeekend
    )


)

@Preview(showBackground = true)
@Composable
private fun PreviewSkillSwapEmpty() {
    SkillSwapTheme { EmptyNotificationsScreen() }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSkillSwapList() {
    SkillSwapTheme { NotificationListScreen(items = sampleNotifications()) }
}