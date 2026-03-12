package com.example.skillswap.viewmodel

import androidx.lifecycle.ViewModel
import com.example.skillswap.model.Signup
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignupViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(Signup())
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

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

//    fun onSignupClick() {
//        val currentState = _uiState.value
//        if (currentState.password != currentState.confirmPassword) {
//            println("Passwords do not match")
//            return
//        }
//
//        auth.createUserWithEmailAndPassword(currentState.email, currentState.password)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    println("Signup successful: ${auth.currentUser?.uid}")
//                } else {
//                    println("Signup failed: ${task.exception?.message}")
//                }
//            }
//    }
fun onSignupClick() {
    val currentState = _uiState.value
    if (currentState.password != currentState.confirmPassword) {
        _uiState.value = currentState.copy(errorMessage = "Passwords do not match")
        return
    }

    _uiState.value = currentState.copy(isLoading = true)

    auth.createUserWithEmailAndPassword(currentState.email, currentState.password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _uiState.value = currentState.copy(isLoading = false, isSuccess = true)
            } else {
                _uiState.value = currentState.copy(
                    isLoading = false,
                    errorMessage = task.exception?.message
                )
            }
        }
}


}