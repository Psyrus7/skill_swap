



package com.example.skillswap.viewmodel

import androidx.lifecycle.ViewModel
import com.example.skillswap.model.Signup
import com.example.skillswap.model.SkillUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignupViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(Signup())
    val uiState: StateFlow<Signup> = _uiState

    private val auth = FirebaseAuth.getInstance()

    // reference to database
    private val database = FirebaseDatabase
        .getInstance()
        .getReference("users")

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

        // STEP 1: Create user in FirebaseAuth
        auth.createUserWithEmailAndPassword(state.email, state.password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    val uid = auth.currentUser!!.uid

                    // STEP 2: Create SkillUser object
                    val skillUser = SkillUser(
                        id = uid,
                        name = state.fullName,
                        profileImage = "",
                        teaches = listOf(state.teachSkill),
                        wantsToLearn = listOf(state.learnSkill),
                        rating = 0f
                    )

                    // STEP 3: Save to Realtime Database
                    database.child(uid).setValue(skillUser)
                        .addOnCompleteListener { dbTask ->

                            if (dbTask.isSuccessful) {

                                _uiState.value = state.copy(
                                    isLoading = false,
                                    isSuccess = true
                                )

                            } else {

                                _uiState.value = state.copy(
                                    isLoading = false,
                                    errorMessage = dbTask.exception?.message
                                )

                            }
                        }

                } else {

                    _uiState.value = state.copy(
                        isLoading = false,
                        errorMessage = task.exception?.message
                    )

                }
            }
    }
}