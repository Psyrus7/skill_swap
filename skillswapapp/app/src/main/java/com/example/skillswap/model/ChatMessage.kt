package com.example.skillswap.model

data class ChatMessage(
    val id: Int,
    val text: String,
    val time: String,
    val isUser: Boolean
)
