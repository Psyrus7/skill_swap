package com.example.skillswap.viewmodel

import androidx.lifecycle.ViewModel

import com.example.skillswap.model.ProfileUIData

import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.FirebaseDatabase

import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel : ViewModel() {
    private val _state = MutableStateFlow(ProfileUIData())
    val state: StateFlow<ProfileUIData> = _state
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().getReference("users")
    fun updateProfile(
        name: String,
        teach: String,
        learn: String
    ) {
        if (name.isBlank()) return
        val currentUserId = auth.currentUser?.uid ?: return
        val teachList = teach.split(",")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
        val learnList = learn.split(",")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
        if (teachList.isEmpty() || learnList.isEmpty()) return
        val updates = mapOf<String, Any>(
            "name" to name,
            "teaches" to teachList,
            "wantsToLearn" to learnList
        )

        database.child(currentUserId).updateChildren(updates)

        _state.value = _state.value.copy(
            name = name,
            canTeach = teachList,
            wantToLearn = learnList
        )
    }
}
 