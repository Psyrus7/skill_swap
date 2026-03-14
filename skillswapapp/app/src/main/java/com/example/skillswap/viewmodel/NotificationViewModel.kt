package com.example.skillswap.viewmodel

import androidx.lifecycle.ViewModel

import com.example.skillswap.model.SwapRequestNotification

import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.*

import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.flow.asStateFlow

class NotificationViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val database = FirebaseDatabase.getInstance().getReference("requests")

    private val _requests = MutableStateFlow<List<SwapRequestNotification>>(emptyList())

    val requests: StateFlow<List<SwapRequestNotification>> = _requests.asStateFlow()

    init {

        listenForRequests()

    }

    private fun listenForRequests() {

        val currentUserId = auth.currentUser?.uid ?: return

        database.orderByChild("receiverId").equalTo(currentUserId)

            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val requestList = mutableListOf<SwapRequestNotification>()

                    for (child in snapshot.children) {

                        val request = child.getValue(SwapRequestNotification::class.java)

                        if (request != null) {

                            requestList.add(request)

                        }

                    }

                    _requests.value = requestList

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

    }

    fun acceptRequest(requestId: String) {

        database.child(requestId).child("status").setValue("accepted")

    }

    fun declineRequest(requestId: String) {

        database.child(requestId).removeValue()

    }

}
