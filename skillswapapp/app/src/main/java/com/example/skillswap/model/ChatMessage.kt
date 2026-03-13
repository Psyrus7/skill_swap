package com.example.skillswap.model

data class ChatMessage(val id: String = "",
                       val conversationId: String = "",
                       val senderId: String = "",
                       val text: String = "",
                       val timestamp: Long = 0
)
