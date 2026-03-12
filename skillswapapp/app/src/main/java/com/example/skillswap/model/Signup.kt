package com.example.skillswap.model

data class Signup(
    val fullName: String = "",
    val email: String = "",
    val teachSkill: String = "",
    val learnSkill: String = "",
    val password: String = "",
    val confirmPassword: String = ""
)
