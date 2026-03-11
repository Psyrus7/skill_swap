package com.example.skillswap.model

data class Conversation(
    val id: Int,
    val name: String,
    val lastMessage: String,
    val time: String,
    val unreadCount: Int,
    val profileRes: Int
)
