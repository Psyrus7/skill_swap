
package com.example.skillswap.view.compose.notifications

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skillswap.viewmodel.NotificationViewModel

@Composable
fun NotificationScreen(
    viewModel: NotificationViewModel = viewModel()
) {
    val items by viewModel.notifications.collectAsState()

    if (items.isEmpty()) {
        EmptyNotificationsScreen()
    } else {
        NotificationsListScreen(items = items)
    }
}

