package com.example.skillswap.repo

import com.example.skillswap.model.Conversation
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MessagesRepository {

    private val db = FirebaseFirestore.getInstance()

    fun listenConversations(
        userId: String,
        onUpdate: (List<Conversation>) -> Unit
    ) {
        db.collection("conversations")
            .whereArrayContains("participants", userId)
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) return@addSnapshotListener

                val conversations = snapshot?.documents?.mapNotNull { doc ->
                    val convo = doc.toObject(Conversation::class.java) ?: return@mapNotNull null
                    val otherUserId = convo.participants.firstOrNull { it != userId } ?: ""
                    val otherUserName = convo.userNames[otherUserId] ?: "Unknown"
                    val otherUserProfile = convo.userProfiles[otherUserId]?:""

                    convo.copy(
                        id = doc.id,
                        otherUserName = otherUserName,
                        otherUserProfile = otherUserProfile
                    )
                } ?: emptyList()

                onUpdate(conversations)
            }
    }
}


