
package com.example.skillswap.viewmodel

import androidx.lifecycle.ViewModel
import com.example.skillswap.model.ProfileUIData
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _state = MutableStateFlow(
        ProfileUIData(
            name = "Alex Thompson",
            rating = 5,
            canTeach = listOf("Guitar","Music Theory"),
            wantToLearn = listOf("Photography","Video Editing")
        )
    )

    val state: StateFlow<ProfileUIData> = _state

    fun logout(){
        auth.signOut()
    }

}