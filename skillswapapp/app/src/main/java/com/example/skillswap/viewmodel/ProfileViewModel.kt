package com.example.skillswap.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skillswap.model.ProfileUIData
import com.example.skillswap.repository.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository = ProfileRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileUIData())
    val state: StateFlow<ProfileUIData> = _state

    init {
        fetchCurrentUserProfile()
    }

    fun fetchCurrentUserProfile() {
        viewModelScope.launch {
            try {
                val user = repository.getCurrentUserProfile()
                if (user != null) {
                    _state.value = ProfileUIData(
                        name = user.name,
                        rating = user.rating.toInt(),
                        canTeach = user.teaches,
                        wantToLearn = user.wantsToLearn,
                        photoUri = user.profileImage
                    )
                } else {
                    Log.d("ProfileViewModel", "No user data found")
                }
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Error fetching profile: ${e.message}")
            }
        }
    }

    fun logout() {
        repository.logout()
    }
}
