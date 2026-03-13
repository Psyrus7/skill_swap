package com.example.skillswap.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.skillswap.model.ProfileUIData
import com.example.skillswap.model.SkillUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().getReference("users")

    private val _state = MutableStateFlow(ProfileUIData())
    val state: StateFlow<ProfileUIData> = _state

    init {
        // Automatically fetch if user is already logged in
        fetchCurrentUserProfile()
    }

    fun fetchCurrentUserProfile() {
        val currentUserId = auth.currentUser?.uid
        if (currentUserId == null) {
            Log.d("ProfileViewModel", "No logged in user found")
            return
        }

        Log.d("ProfileViewModel", "Fetching data for UID: $currentUserId")

        database.child(currentUserId).get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val user = snapshot.getValue(SkillUser::class.java)
                Log.d("ProfileViewModel", "User data retrieved: $user")

                if (user != null) {
                    _state.value = ProfileUIData(
                        name = user.name,
                        rating = user.rating.toInt(),
                        canTeach = user.teaches,
                        wantToLearn = user.wantsToLearn,
                        photoUri = user.profileImage
                    )
                }
            } else {
                Log.d("ProfileViewModel", "No snapshot found for UID: $currentUserId")
            }
        }.addOnFailureListener {
            Log.e("ProfileViewModel", "Error fetching profile: ${it.message}")
        }
    }

    fun logout() {
        auth.signOut()
    }
}