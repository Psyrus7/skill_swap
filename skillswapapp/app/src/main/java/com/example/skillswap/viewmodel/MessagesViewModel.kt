package com.example.skillswap.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.skillswap.R
import com.example.skillswap.model.Conversation

class MessagesViewModel: ViewModel(){
    private val _conversations = mutableStateListOf<Conversation>()
    val conversations: List<Conversation> get() = _conversations

    init {
        _conversations.addAll(
            listOf(
                Conversation(1, "Jack King", "Type.....", "Now", 8, R.drawable.skillswaplogo),
                Conversation(2, "Michael", "Is the apartment still available?", "8:05 PM", 0, R.drawable.skillswaplogo),
                Conversation(3, "Patel, G", "A place to slow down,", "1 hour ago", 2, R.drawable.skillswaplogo),
                Conversation(4, "Johan. K", "A place to slow down,", "Yesterday", 0, R.drawable.skillswaplogo)
            )
        )
    }
}
