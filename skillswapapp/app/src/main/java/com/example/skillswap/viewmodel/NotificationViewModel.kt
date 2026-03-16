package com.example.skillswap.viewmodel
import androidx.lifecycle.ViewModel
import com.example.skillswap.repo.NotificationRepository
class NotificationViewModel : ViewModel() {
    private val repository = NotificationRepository()
    val requests = repository.requests
    fun acceptRequest(requestId: String) {
        repository.acceptRequest(requestId)
    }
    fun declineRequest(requestId: String) {
        repository.declineRequest(requestId)
    }
    fun getCurrentUserId(): String {
        return repository.getCurrentUserId()
    }
}