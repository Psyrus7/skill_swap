package com.example.skillswap.model

data class ProfileUIData(

    val name: String? = null,
    val rating: Int? =0,
    val canTeach: List<String>? = null,   // <-- List
    val wantToLearn: List<String>? = null,
    val photoUri: String? = null
)
