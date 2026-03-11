package com.example.skillswap.model

data class ProfileUIData(
    val name: String,
    val rating: Int,               // 0..5
    val canTeach: String,
    val wantToLearn: String
)
