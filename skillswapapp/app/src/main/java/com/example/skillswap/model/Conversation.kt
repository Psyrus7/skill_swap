package com.example.skillswap.model

data class Conversation(
    val id: String = "",
    val participants: List<String> = emptyList(),
    val lastMessage: String = "",
    val timestamp: Long = 0,
    val unreadCount: Int = 0,
    val otherUserName: String = "",
    val otherUserProfile: String = ""
)
