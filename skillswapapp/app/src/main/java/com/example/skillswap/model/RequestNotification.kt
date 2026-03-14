package com.example.skillswap.model

data class SwapRequestNotification(
    val id: String = "",
    val senderId: String = "",
    val senderName: String = "",
    val receiverId: String = "",
    val message: String = "",
    val receiverName:String ="",
    val status: String = "pending"
)