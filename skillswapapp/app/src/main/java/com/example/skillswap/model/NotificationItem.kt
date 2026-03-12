package com.example.skillswap.model

enum class NotificationSection { Today, Yesterday, ThisWeekend }

data class NotificationItem(
    val id: String,
    val title: String,
    val body: String,
    val time: String,               // "10m ago", "8:00 AM", "Apr 26, 2024"
    val leadingEmoji: String? = null,
    val highlightText: String? = null, // part of title to tint (e.g., “Coding”, “Guitar”)
    val actionLabel: String? = null,   // optional pill action (“Chat”, “View”, “Accept”)
    val section: NotificationSection
)

