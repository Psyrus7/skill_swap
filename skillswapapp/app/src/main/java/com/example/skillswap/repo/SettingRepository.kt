package com.example.skillswap.repo


import com.example.skillswap.model.ProfileUIData

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest

import com.google.firebase.database.FirebaseDatabase

class SettingsRepository {

    private val auth = FirebaseAuth.getInstance()

    private val database = FirebaseDatabase.getInstance().getReference("users")

    fun getCurrentUserId(): String? {

        return auth.currentUser?.uid

    }

    fun updateProfile(
        name: String,
        teachList: List<String>,
        learnList: List<String>
    ) {
        val uid = auth.currentUser?.uid ?: return
        // Update Firebase Auth profile
        val profileUpdates = userProfileChangeRequest {
            displayName = name
        }
        auth.currentUser?.updateProfile(profileUpdates)
        // Update Realtime Database
        val updates = mapOf<String, Any>(
            "name" to name,
            "teaches" to teachList,
            "wantsToLearn" to learnList
        )
        database.child(uid).updateChildren(updates)
    }

    fun loadProfile(onResult: (ProfileUIData) -> Unit) {

        val uid = auth.currentUser?.uid ?: return

        database.child(uid).get().addOnSuccessListener { snapshot ->

            val name = snapshot.child("name").value?.toString() ?: ""

            val teaches = snapshot.child("teaches")

                .children.mapNotNull { it.value?.toString() }

            val learns = snapshot.child("wantsToLearn")

                .children.mapNotNull { it.value?.toString() }

            onResult(

                ProfileUIData(

                    name = name,

                    canTeach = teaches,

                    wantToLearn = learns

                )

            )

        }

    }

}
