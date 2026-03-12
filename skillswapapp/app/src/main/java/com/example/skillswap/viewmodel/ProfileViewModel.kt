package com.example.skillswap.viewmodel


import androidx.lifecycle.ViewModel
import com.example.skillswap.model.ProfileUIData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        ProfileUIData()
    )

    val uiState = _uiState.asStateFlow()  // expose read-only

    fun onEditClicked() {
        // TODO: navigate to edit profile or update state
    }

    fun onBackClicked() {
        // TODO: handle back navigation if needed
    }



    fun updateProfile(
        name: String?,
        rating: Int?=null,
        canTeach: List<String>?,
        wantToLearn: List<String>?,
        photoUri: String? = null

    ) {

        val current = _uiState.value

        _uiState.value = current.copy(
            name      = name        ?: current.name,
            rating        = rating      ?: current.rating,
            canTeach    = canTeach?.ifEmpty { null } ?: current.canTeach,
            wantToLearn = wantToLearn?.ifEmpty { null } ?: current.wantToLearn,
            photoUri = photoUri ?: current.photoUri

        )

    }

}

