package com.example.skillswap.repo

import com.example.skillswap.model.ChatMessage
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.firestore.FieldValue

import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.firestore.SetOptions

class ChatRepository {
    private val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    val currentUserId:String? get() = FirebaseAuth.getInstance().uid
    val currentUserName:String get() = FirebaseAuth.getInstance().currentUser?.displayName?: FirebaseAuth.getInstance().currentUser?.email?:"Unknown User"
    fun sendMessage(
        message: ChatMessage,
        currentUserName: String,
        otherUserName: String,
        currentUserProfile: String = "",
        otherUserProfile: String = ""
    ) {
        val messageData = hashMapOf(
            "conversationId" to message.conversationId,
            "senderId" to message.senderId,
            "text" to message.text,
            "timestamp" to FieldValue.serverTimestamp()
        )
        db.collection("messages")
            .add(messageData)
        val participants = message.conversationId.split("_")
        if (participants.size != 2) return
        val otherUserId = participants.first { it != message.senderId }
        db.collection("conversations")
            .document(message.conversationId)
            .set(
                mapOf(
                    "lastMessage" to message.text,
                    "timestamp" to FieldValue.serverTimestamp(),
                    "participants" to participants,
                    "userNames" to mapOf(
                        message.senderId to currentUserName,
                        otherUserId to otherUserName
                    ),
                    "userProfiles" to mapOf(
                        message.senderId to currentUserProfile,
                        otherUserId to otherUserProfile
                    )
                ),
                SetOptions.merge()
            )
    }

    fun listenMessages(
        conversationId: String,
        onUpdate: (List<ChatMessage>) -> Unit
    ) {
        db.collection("messages")
            .whereEqualTo("conversationId", conversationId)
            .orderBy("timestamp")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    println("Firestore error: $error")
                    return@addSnapshotListener
                }
                val messages = snapshot?.documents?.mapNotNull {
                    it.toObject(ChatMessage::class.java)?.copy(id = it.id)
                } ?: emptyList()
                onUpdate(messages)
            }
    }
}
