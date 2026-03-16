package com.example.skillswap.viewmodel

import androidx.lifecycle.ViewModel
import com.example.skillswap.model.ChatMessage
import com.example.skillswap.repo.ChatRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChatViewModel : ViewModel() {

    private val repository = ChatRepository()
     val auth = repository.auth
    val currentUserId:String? get() = repository.currentUserId
    val currentUserName:String get() = repository.currentUserName
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    fun listenMessages(conversationId: String) {
        repository.listenMessages(conversationId) { messageList ->
            _messages.value = messageList
        }

    }

    fun sendMessage(
        text: String,
        conversationId: String,
        otherUserName: String
    ) {
        if (text.isBlank()) return
        val senderId = currentUserId?:return
        val message = ChatMessage(
            conversationId = conversationId,
            senderId = senderId,
            text = text
        )
        repository.sendMessage(
            message,
            currentUserName,
            otherUserName
        )    }
}


