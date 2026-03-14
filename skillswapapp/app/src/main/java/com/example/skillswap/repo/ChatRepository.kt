package com.example.skillswap.repo

import com.example.skillswap.model.ChatMessage

import com.google.firebase.firestore.FieldValue

import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.firestore.SetOptions

class ChatRepository {

    private val db = FirebaseFirestore.getInstance()

    fun sendMessage(

        message: ChatMessage,

        currentUserName: String = "",

        otherUserName: String = ""

    ) {

        val docRef = db.collection("messages").document()

        val messageData = hashMapOf(

            "conversationId" to message.conversationId,

            "senderId" to message.senderId,

            "text" to message.text,

            "timestamp" to FieldValue.serverTimestamp()

        )

        docRef.set(messageData)

        val participants = message.conversationId.split("_")

        if (participants.size != 2) return

        db.collection("conversations")

            .document(message.conversationId)

            .set(

                mapOf(

                    "lastMessage" to message.text,

                    "timestamp" to FieldValue.serverTimestamp(),

                    "participants" to participants,

                    "userNames" to mapOf(

                        message.senderId to currentUserName,

                        participants.first { it != message.senderId } to otherUserName

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