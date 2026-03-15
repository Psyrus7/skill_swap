package com.example.skillswap.viewmodel

import androidx.lifecycle.ViewModel

import com.example.skillswap.model.SwapRequestNotification

import com.example.skillswap.repo.SwapRequestRepository

import com.google.firebase.auth.FirebaseAuth

import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.flow.asStateFlow

class NotificationViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val repository = SwapRequestRepository()

    private val _requests = MutableStateFlow<List<SwapRequestNotification>>(emptyList())

    val requests: StateFlow<List<SwapRequestNotification>> = _requests.asStateFlow()

    private var receivedRequests: List<SwapRequestNotification> = emptyList()

    private var sentRequests: List<SwapRequestNotification> = emptyList()

    init {

        listenForRequests()

    }

    private fun listenForRequests() {

        val currentUserId = auth.currentUser?.uid ?: return

        // listen incoming requests

        repository.listenRequests(currentUserId) { received ->

            receivedRequests = received

            updateList()

        }

        // listen sent requests

        repository.listenSentRequests(currentUserId) { sent ->

            sentRequests = sent

            updateList()

        }

    }

    private fun updateList() {

        _requests.value = receivedRequests + sentRequests

    }

    fun acceptRequest(requestId: String) {

        repository.acceptRequest(requestId) {}

    }

    fun declineRequest(requestId: String) {

        repository.deleteRequest(requestId) {}

    }

}
