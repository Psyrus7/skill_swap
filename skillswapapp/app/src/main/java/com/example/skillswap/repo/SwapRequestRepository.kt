package com.example.skillswap.repo

import com.example.skillswap.model.SwapRequestNotification

import com.google.firebase.database.*

class SwapRequestRepository {

    private val db = FirebaseDatabase.getInstance().getReference("requests")

    fun sendRequest(request: SwapRequestNotification, onComplete: (Boolean) -> Unit) {

        db.child(request.id).setValue(request)

            .addOnCompleteListener { task ->

                onComplete(task.isSuccessful)

            }

    }

    fun listenRequests(

        currentUserId: String,

        onUpdate: (List<SwapRequestNotification>) -> Unit

    ) {

        db.orderByChild("receiverId").equalTo(currentUserId)

            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val list = mutableListOf<SwapRequestNotification>()

                    for (child in snapshot.children) {

                        val item = child.getValue(SwapRequestNotification::class.java)

                        if (item != null) {

                            list.add(item)

                        }

                    }

                    onUpdate(list)

                }

                override fun onCancelled(error: DatabaseError) {}

            })

    }

    // 🔔 Listener for sender to know request was accepted

    fun listenSentRequests(

        senderId: String,

        onUpdate: (List<SwapRequestNotification>) -> Unit

    ) {

        db.orderByChild("senderId").equalTo(senderId)

            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val list = mutableListOf<SwapRequestNotification>()

                    for (child in snapshot.children) {

                        val item = child.getValue(SwapRequestNotification::class.java)

                        if (item != null) {

                            list.add(item)

                        }

                    }

                    onUpdate(list)

                }

                override fun onCancelled(error: DatabaseError) {}

            })

    }

    fun acceptRequest(requestId: String, onComplete: (Boolean) -> Unit) {

        db.child(requestId).child("status").setValue("accepted")

            .addOnCompleteListener { task ->

                onComplete(task.isSuccessful)

            }

    }

    fun deleteRequest(requestId: String, onComplete: (Boolean) -> Unit) {

        db.child(requestId).removeValue()

            .addOnCompleteListener { task ->

                onComplete(task.isSuccessful)

            }

    }

}
