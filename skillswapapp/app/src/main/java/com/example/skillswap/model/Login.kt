package com.example.skillswap.model


data class Login(
        val email: String = "",
        val password: String = "",
        val isLoading: Boolean = false,
        val isSuccess: Boolean = false,
        val error: String? = null
)


