package com.example.skillswap.viewmodel

import androidx.lifecycle.ViewModel
import com.example.skillswap.model.Login
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel: ViewModel() {
    private val _state = MutableStateFlow(Login())
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val state: StateFlow<Login> = _state

    fun updateEmail(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun updatePassword(password: String) {
        _state.value = _state.value.copy(password = password)
    }

//    fun login() {
//
//        val email = _state.value.email
//        val password = _state.value.password
//
//        if (email.isEmpty() || password.isEmpty()) {
//            _state.value = _state.value.copy(error = "Fields cannot be empty")
//            return
//        }
//
//        _state.value = _state.value.copy(isLoading = true)
//
//        // Simulate login
//        _state.value = _state.value.copy(
//            isLoading = false,
//            error = null
//        )
//    }
fun login() {
    val email = _state.value.email
    val password = _state.value.password

    if (email.isEmpty() || password.isEmpty()) {
        _state.value = _state.value.copy(error = "Fields cannot be empty")
        return
    }

    _state.value = _state.value.copy(isLoading = true)

    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    isSuccess = true,
                    error = null
                )
                println("Login successful: ${auth.currentUser?.uid}")
            } else {
                _state.value = _state.value.copy(
                    isLoading = false,
                    isSuccess = false,
                    error = task.exception?.message
                )
                println("Login failed: ${task.exception?.message}")

            }
        }
}

}