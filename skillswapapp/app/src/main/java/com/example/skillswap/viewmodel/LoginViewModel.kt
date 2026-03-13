package com.example.skillswap.viewmodel

import AuthRepository
import androidx.lifecycle.ViewModel
import com.example.skillswap.model.Login
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(Login())
    val state: StateFlow<Login> = _state

    fun updateEmail(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun updatePassword(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun login() {
        val email = _state.value.email
        val password = _state.value.password

        if (email.isEmpty() || password.isEmpty()) {
            _state.value = _state.value.copy(error = "Fields cannot be empty")
            return
        }

        _state.value = _state.value.copy(isLoading = true)

        repository.login(email, password) { success, error ->
            if (success) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    isSuccess = true,
                    error = null
                )
                println("Login successful: ${repository.getCurrentUserId()}")
            } else {
                _state.value = _state.value.copy(
                    isLoading = false,
                    isSuccess = false,
                    error = error
                )
                println("Login failed: $error")
            }
        }
    }
}

