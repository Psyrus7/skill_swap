package com.example.skillswap.repo

import com.example.skillswap.model.SwapRequestNotification

import com.google.firebase.auth.FirebaseAuth

import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.flow.asStateFlow

class NotificationRepository {

    private val auth = FirebaseAuth.getInstance()

    private val swapRepository = SwapRequestRepository()

    private val _requests = MutableStateFlow<List<SwapRequestNotification>>(emptyList())

    val requests: StateFlow<List<SwapRequestNotification>> = _requests.asStateFlow()

    private var receivedRequests: List<SwapRequestNotification> = emptyList()

    private var sentRequests: List<SwapRequestNotification> = emptyList()

    init {

        listenForNotifications()

    }

    private fun listenForNotifications() {

        val currentUserId = auth.currentUser?.uid ?: return

        // Listen for received swap requests

        swapRepository.listenRequests(currentUserId) { received ->

            receivedRequests = received

            updateNotifications()

        }

        // Listen for sent swap requests

        swapRepository.listenSentRequests(currentUserId) { sent ->

            sentRequests = sent

            updateNotifications()

        }

    }

    private fun updateNotifications() {

        _requests.value = receivedRequests + sentRequests

    }

    fun acceptRequest(requestId: String) {

        swapRepository.acceptRequest(requestId) {}

    }

    fun declineRequest(requestId: String) {

        swapRepository.deleteRequest(requestId) {}

    }
    fun getCurrentUserId() : String{
        return auth.currentUser?.uid ?: ""
    }

}
