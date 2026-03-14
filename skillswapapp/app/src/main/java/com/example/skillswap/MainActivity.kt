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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.skillswap.ui.theme.SkillSwapTheme
import com.example.skillswap.view.compose.LoginScreen
import com.example.skillswap.view.compose.MatchedScreenCard
import com.example.skillswap.view.compose.MessageScreen
import com.example.skillswap.view.compose.MessagesListScreen
import com.example.skillswap.view.compose.ProfileScreen
import com.example.skillswap.view.compose.SettingsScreen
import com.example.skillswap.view.compose.SignupAndLoginScreen
import com.example.skillswap.view.compose.SignupScreen
import com.example.skillswap.view.compose.SkillSwapHomeScreen
import com.example.skillswap.view.compose.notifications.EmptyNotificationsScreen
import com.example.skillswap.view.compose.notifications.NotificationScreen
import com.example.skillswap.view.compose.notifications.NotificationsListScreen
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

    NavHost(

        navController = navController,

        startDestination = "signupAndLoginScreen"

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

            MessagesListScreen(navController = navController)

        }

        composable("chat/{conversationId}/{userName}") { backStackEntry ->

            val conversationId =

                backStackEntry.arguments?.getString("conversationId") ?: ""

            val userName =

                backStackEntry.arguments?.getString("userName") ?: ""

            MessageScreen(

                navController = navController,

                conversationId = conversationId,

                userName = userName

            )

        }

        composable("matchedScreenCard") {

            MatchedScreenCard(

                navController = navController,

                name = "DFAS",

                receiverId = "sampleReceiverId",

                onSendRequest = {},

                onMessage = {}

            )

        }

        composable("profileScreen") {

            ProfileScreen(

                navController = navController

            )

        }

        composable("notificationScreen") {

            NotificationScreen(navController = navController)

        }

        composable("setting") {

            SettingsScreen(

                navController = navController

            )

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