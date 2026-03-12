package com.example.skillswap.viewmodel

import androidx.lifecycle.ViewModel
import com.example.skillswap.model.NotificationItem
import com.example.skillswap.view.compose.notifications.sampleNotifications
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotificationViewModel : ViewModel() {

    private val _notifications = MutableStateFlow<List<NotificationItem>>(emptyList())
    val notifications: StateFlow<List<NotificationItem>> = _notifications.asStateFlow()

    init {
        loadNotifications()
    }

    private fun loadNotifications() {
        _notifications.value = sampleNotifications()
    }

    fun setNotifications(newNotifications: List<NotificationItem>) {
        _notifications.value = newNotifications
    }

    fun clearNotifications() {
        _notifications.value = emptyList()
    }
}

