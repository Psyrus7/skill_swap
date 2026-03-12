package com.example.skillswap.view.compose.notifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.skillswap.ui.theme.SkillSwapTheme

class NotificationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SkillSwapTheme {
                val items = sampleNotifications()
                if (items.isEmpty()) {
                    EmptyNotificationsScreen()
                } else {
                    NotificationListScreen(items = items) // no action buttons
                }
            }
        }
    }
}