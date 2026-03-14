package com.example.skillswap.viewmodel

import androidx.lifecycle.ViewModel
import com.example.skillswap.model.ChatMessage
import com.example.skillswap.repo.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChatViewModel : ViewModel() {

    private val repository = ChatRepository()

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
        senderId: String,
        currentUserName: String,
        otherUserName: String
    ) {
        if (text.isBlank()) return
        val message = ChatMessage(
            conversationId = conversationId,
            senderId = senderId,
            text = text
        )
        repository.sendMessage(message, currentUserName, otherUserName)
    }
}


