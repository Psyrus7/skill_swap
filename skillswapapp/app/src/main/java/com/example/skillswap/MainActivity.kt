package com.example.skillswap

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.skillswap.ui.theme.SkillSwapTheme
import com.example.skillswap.view.compose.*
import com.example.skillswap.view.compose.notifications.*
import com.example.skillswap.viewmodel.HomeScreenViewModel
import com.example.skillswap.viewmodel.LoginViewModel
import com.example.skillswap.viewmodel.SignupViewModel
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            SkillSwapTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) {

                    AppNavigation()

                }

            }

        }

    }

}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val auth = FirebaseAuth.getInstance()

    // Check if user already logged in
    val startScreen =
        if (auth.currentUser != null) {
            "homeScreen"
        } else {
            "signupAndLoginScreen"
        }

    NavHost(
        navController = navController,
        startDestination = startScreen
    ) {

        composable("signupAndLoginScreen") {
            SignupAndLoginScreen(navController)
        }

        composable("signupScreen") {
            val viewModel: SignupViewModel = viewModel()
            SignupScreen(viewModel, navController)
        }

        composable("loginScreen") {
            val viewModel: LoginViewModel = viewModel()
            LoginScreen(viewModel, navController)
        }

        composable("homeScreen") {
            val viewModel: HomeScreenViewModel = viewModel()

            SkillSwapHomeScreen(
                modifier = Modifier,
                viewModel = viewModel,
                navController = navController
            )
        }

        composable("messageListScreen") {
            MessagesListScreen(navController)
        }

        composable("chatScreen") {
            MessageScreen(navController)
        }

        composable("matchedScreenCard") {
            MatchedScreenCard(
                navController = navController,
                name = "DFAS",
                onSendRequest = {},
                onMessage = {
                    navController.navigate("chatScreen")
                }
            )
        }

        composable("profileScreen") {
            ProfileScreen(navController)
        }

        composable("notificationScreen") {

            val items = sampleNotifications()

            if (items.isEmpty()) {
                EmptyNotificationsScreen(navController)
            } else {
                NotificationsListScreen(
                    navController = navController,
                    items = items
                )
            }
        }

        composable("setting") {
            SettingsScreen(navController)
        }
    }
}



@Preview(showBackground = true)

@Composable
fun GreetingPreview() {

    SkillSwapTheme {

        AppNavigation()

    }

}
