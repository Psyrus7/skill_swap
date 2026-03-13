



package com.example.skillswap.viewmodel

import androidx.lifecycle.ViewModel
import com.example.skillswap.model.Signup
import com.example.skillswap.repo.SignupRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class SignupViewModel(
    private val repository: SignupRepository = SignupRepository()
) : ViewModel() {

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
        val state = _uiState.value

        if (state.password != state.confirmPassword) {
            _uiState.value = state.copy(errorMessage = "Passwords do not match")
            return
        }

        _uiState.value = state.copy(isLoading = true)

        repository.signup(
            fullName = state.fullName,
            email = state.email,
            password = state.password,
            teachSkill = state.teachSkill,
            learnSkill = state.learnSkill
        ) { success, error ->
            if (success) {
                _uiState.value = state.copy(
                    isLoading = false,
                    isSuccess = true
                )
            } else {
                _uiState.value = state.copy(
                    isLoading = false,
                    errorMessage = error
                )
            }
        }
    }
}



