package com.example.skillswap.view.compose.notifications
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.skillswap.viewmodel.NotificationViewModel
import com.google.firebase.auth.FirebaseAuth
@Composable
fun NotificationScreen(
    navController: NavController,
    viewModel: NotificationViewModel = viewModel()
) {
    val requests by viewModel.requests.collectAsState()
    if (requests.isEmpty()) {
        EmptyNotificationsScreen(
            navController = navController
        )
    } else {
        NotificationsListScreen(
            navController = navController,
            requests = requests,
            onAccept = { request ->
                viewModel.acceptRequest(request.id)
                val currentUserId = FirebaseAuth.getInstance().uid ?: return@NotificationsListScreen
                val conversationId = listOf(currentUserId, request.senderId)
                    .sorted()
                    .joinToString("_")
                val encodedName = Uri.encode(request.senderName)
                navController.navigate("chat/$conversationId/$encodedName")
            },
            onDecline = { request ->
                viewModel.declineRequest(request.id)
            }
        )
    }
}