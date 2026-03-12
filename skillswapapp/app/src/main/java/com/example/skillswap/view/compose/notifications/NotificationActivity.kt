

package com.example.skillswap.view.compose.notifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.skillswap.ui.theme.SkillSwapTheme
import com.example.skillswap.viewmodel.NotificationViewModel

class NotificationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            SkillSwapTheme {

                val navController = rememberNavController()
                val viewModel: NotificationViewModel = viewModel()

                NotificationScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}

