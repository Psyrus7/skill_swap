package com.example.skillswap.viewmodel

import androidx.lifecycle.ViewModel
import com.example.skillswap.model.Signup
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignupViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(Signup())
    val uiState: StateFlow<Signup> = _uiState

    fun onFullNameChange(value: String) {
        _uiState.value = _uiState.value.copy(fullName = value)
    }

    fun onEmailChange(value: String) {
        _uiState.value = _uiState.value.copy(email = value)
    }

    fun onTeachSkillChange(value: String) {
        _uiState.value = _uiState.value.copy(teachSkill = value)
    }

    fun onLearnSkillChange(value: String) {
        _uiState.value = _uiState.value.copy(learnSkill = value)
    }

    fun onPasswordChange(value: String) {
        _uiState.value = _uiState.value.copy(password = value)
    }

    fun onConfirmPasswordChange(value: String) {
        _uiState.value = _uiState.value.copy(confirmPassword = value)
    }

    fun onSignupClick() {
        println("Signup clicked")
    }
}