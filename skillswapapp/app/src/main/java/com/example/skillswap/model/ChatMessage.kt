package com.example.skillswap.model

import com.google.firebase.firestore.DocumentId
import java.util.Date

data class ChatMessage(
    val id: String = "",
    val conversationId: String = "",
    val senderId: String = "",
    val text: String = "",
    val timestamp: Date? = null
)
