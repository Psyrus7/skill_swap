package com.example.skillswap.repo

import com.example.skillswap.model.ChatMessage
import com.google.firebase.firestore.FirebaseFirestore

class ChatRepository {
    private val db = FirebaseFirestore.getInstance()

    fun sendMessage(message: ChatMessage) {

        db.collection("messages")

            .add(message)

        db.collection("conversations")

            .document(message.conversationId)

            .set(

                mapOf(

                    "lastMessage" to message.text,

                    "timestamp" to System.currentTimeMillis()

                ),
                com.google.firebase.firestore.SetOptions.merge()

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
                if(error!=null){
                    println("FireStore error: $error")
                    return@addSnapshotListener
                }

                val messages = snapshot?.documents?.mapNotNull {

                    it.toObject(ChatMessage::class.java)

                } ?: emptyList()

                onUpdate(messages)

            }

    }

}