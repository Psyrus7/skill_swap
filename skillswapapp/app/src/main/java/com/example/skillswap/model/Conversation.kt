package com.example.skillswap.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Conversation(
    @DocumentId val id: String = "",
    val participants: List<String> = emptyList(),
    val lastMessage: String = "",
    @ServerTimestamp val timestamp: Date? = null,
    val unreadCount: Int = 0,
    val userNames: Map<String, String> = emptyMap(),
    val userProfiles: Map<String, String> = emptyMap(),
    val otherUserProfile: String = "",
    val otherUserName: String = ""

)
