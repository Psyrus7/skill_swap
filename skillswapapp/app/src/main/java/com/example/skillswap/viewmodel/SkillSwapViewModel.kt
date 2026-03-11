package com.example.skillswap.viewmodel

import androidx.lifecycle.ViewModel
import com.example.skillswap.model.SkillUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow



    class SkillSwapViewModel : ViewModel() {

        private val _searchText = MutableStateFlow("")
        val searchText: StateFlow<String> = _searchText

        private val _users = MutableStateFlow(
            listOf(
                SkillUser(
                    id = "1",
                    name = "Alex Thompson",
                    profileImage = "",
                    teaches = listOf("Guitar", "Music Theory"),
                    wantsToLearn = listOf("Photography", "Video Editing"),
                    rating = 5f
                ),
                SkillUser(
                    id = "2",
                    name = "Sophia Lee",
                    profileImage = "",
                    teaches = listOf("Spanish"),
                    wantsToLearn = listOf("Cooking"),
                    rating = 4f
                ),
                SkillUser(
                    id = "3",
                    name = "Daniel Scott",
                    profileImage = "",
                    teaches = listOf("Yoga"),
                    wantsToLearn = listOf("Guitar"),
                    rating = 5f
                ),
                SkillUser(
                    id = "4",
                    name = "Emma Watson",
                    profileImage = "",
                    teaches = listOf("Photography"),
                    wantsToLearn = listOf("Yoga"),
                    rating = 4f
                )
            )
        )
        val users: StateFlow<List<SkillUser>> = _users

        fun onSearchTextChange(value: String) {
            _searchText.value = value
        }

        fun filteredUsers(): List<SkillUser> {
            val query = _searchText.value.trim().lowercase()
            if (query.isEmpty()) return _users.value

            return _users.value.filter { user ->
                user.name.lowercase().contains(query) ||
                        user.teaches.any { it.lowercase().contains(query) } ||
                        user.wantsToLearn.any { it.lowercase().contains(query) }
            }
        }
    }
