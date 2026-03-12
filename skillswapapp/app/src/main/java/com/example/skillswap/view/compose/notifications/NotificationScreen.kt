package com.example.skillswap.view.compose.notifications




import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skillswap.ui.theme.SkillSwapTheme
import com.example.skillswap.viewmodel.NotificationViewModel

class NotificationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SkillSwapTheme {
                val viewModel: NotificationViewModel = viewModel()
                NotificationScreen(viewModel = viewModel)
            }
        }
    }
}
