package com.example.skillswap.viewmodel

import androidx.lifecycle.ViewModel

import com.example.skillswap.model.Conversation

import com.example.skillswap.repo.MessagesRepository

import com.google.firebase.auth.FirebaseAuth

import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.StateFlow

class MessagesViewModel : ViewModel() {

    private val repository = MessagesRepository()

    private val _conversations =

        MutableStateFlow<List<Conversation>>(emptyList())

    val conversations: StateFlow<List<Conversation>> =

        _conversations

    init {

        val userId = FirebaseAuth.getInstance().uid?:""

        repository.listenConversations(userId) {

            _conversations.value = it

        }

    }

}

