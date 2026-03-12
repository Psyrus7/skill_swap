package com.example.skillswap.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.skillswap.model.ChatMessage

class ChatViewModel : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    init {
        // Initial dummy messages
        _messages.value = listOf(
            ChatMessage(1, "Hi! I'm interested in your teachings. is it still available", "12:43 PM", true),
            ChatMessage(2, "Yes, it's still available! would you like to schedule a viewing", "11:50 AM", false),
            ChatMessage(3, "Can you meeting with tomorrow?", "11:50 AM", false),
            ChatMessage(4, "Please let me know when you're available.", "11:50 AM", false),
            ChatMessage(5, "Sure", "12:43 PM", true)
        )
    }

    fun sendMessage(text: String) {
        viewModelScope.launch {
            val newMessage = ChatMessage(
                id = _messages.value.size + 1,
                text = text,
                time = "Now",
                isUser = true
            )
            _messages.value = _messages.value + newMessage
        }
    }
}
