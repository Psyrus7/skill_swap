package com.example.skillswap.repository

import com.example.skillswap.model.SkillUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class ProfileRepository {

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().getReference("users")

    suspend fun getCurrentUserProfile(): SkillUser? {
        val currentUserId = auth.currentUser?.uid ?: return null
        val snapshot = database.child(currentUserId).get().await()
        return snapshot.getValue(SkillUser::class.java)
    }

    fun logout() {
        auth.signOut()
    }
}
