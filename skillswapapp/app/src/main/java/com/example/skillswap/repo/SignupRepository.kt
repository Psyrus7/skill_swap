package com.example.skillswap.repo

import com.example.skillswap.model.SkillUser

import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.auth.userProfileChangeRequest

import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.FirebaseDatabase

class SignupRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")
) {
    fun signup(
        fullName: String,
        email: String,
        password: String,
        teachSkill: String,
        learnSkill: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = auth.currentUser
                    val uid = currentUser?.uid ?: run {
                        onResult(false, "User ID not found")
                        return@addOnCompleteListener
                    }
                    val profileUpdates = userProfileChangeRequest {
                        displayName = fullName
                    }
                    currentUser.updateProfile(profileUpdates)
                        .addOnCompleteListener { profileTask ->
                            if (!profileTask.isSuccessful) {
                                onResult(false, profileTask.exception?.message)
                                return@addOnCompleteListener
                            }
                            val skillUser = SkillUser(
                                id = uid,
                                name = fullName,
                                profileImage = "",
                                teaches = listOf(teachSkill),
                                wantsToLearn = listOf(learnSkill),
                                rating = 0f
                            )
                            database.child(uid).setValue(skillUser)
                                .addOnCompleteListener { dbTask ->
                                    if (dbTask.isSuccessful) {
                                        onResult(true, null)
                                    } else {
                                        onResult(false, dbTask.exception?.message)
                                    }
                                }
                        }
                } else {
                    onResult(false, task.exception?.message)
                }
            }
    }
}
