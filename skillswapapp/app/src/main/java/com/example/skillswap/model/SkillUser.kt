package com.example.skillswap.model


data class SkillUser(
    val id: String = "",
    val name: String = "",
    val profileImage: String = "",
    val teaches: List<String> = emptyList(),
    val wantsToLearn: List<String> = emptyList(),
    val rating: Float = 0f
)