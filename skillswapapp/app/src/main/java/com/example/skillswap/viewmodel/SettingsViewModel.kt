package com.example.skillswap.viewmodel

import androidx.lifecycle.ViewModel

import com.example.skillswap.model.ProfileUIData

import com.example.skillswap.repo.SettingsRepository

import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel : ViewModel() {

    private val repository = SettingsRepository()

    private val _state = MutableStateFlow(ProfileUIData())

    val state: StateFlow<ProfileUIData> = _state

    init {

        loadProfile()

    }

    private fun loadProfile() {

        repository.loadProfile { profile ->

            _state.value = profile

        }

    }

    fun updateProfile(

        name: String,

        teach: String,

        learn: String

    ) {

        if (name.isBlank()) return

        val teachList = teach.split(",")

            .map { it.trim() }

            .filter { it.isNotEmpty() }

        val learnList = learn.split(",")

            .map { it.trim() }

            .filter { it.isNotEmpty() }

        if (teachList.isEmpty() || learnList.isEmpty()) return

        repository.updateProfile(

            name = name,

            teachList = teachList,

            learnList = learnList

        )

        _state.value = _state.value.copy(

            name = name,

            canTeach = teachList,

            wantToLearn = learnList

        )

    }

}
 