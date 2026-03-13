package com.example.skillswap.viewmodel


import androidx.lifecycle.ViewModel
import com.example.skillswap.model.ProfileUIData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel: ViewModel() {

    private val _state = MutableStateFlow(ProfileUIData())
    val state: StateFlow<ProfileUIData> = _state

    fun updateProfile(
        name:String,
        teach:String,
        learn:String
    ){

        _state.value = _state.value.copy(
            name = name,
            canTeach = teach.split(","),
            wantToLearn = learn.split(",")
        )

    }

}


