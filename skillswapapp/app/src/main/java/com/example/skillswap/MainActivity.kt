package com.example.skillswap

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.skillswap.model.NotificationItem
import com.example.skillswap.ui.theme.SkillSwapTheme
import com.example.skillswap.view.compose.LoginScreen
import com.example.skillswap.view.compose.MatchedScreenCard
import com.example.skillswap.view.compose.MessageListScreen
import com.example.skillswap.view.compose.MessageScreen
import com.example.skillswap.view.compose.MessagesListScreen
import com.example.skillswap.view.compose.ProfileScreen
import com.example.skillswap.view.compose.SettingsScreen
import com.example.skillswap.view.compose.SignupAndLoginScreen
import com.example.skillswap.view.compose.SignupScreen
import com.example.skillswap.view.compose.SkillSwapHomeScreen
import com.example.skillswap.view.compose.notifications.NotificationListScreen
import com.example.skillswap.viewmodel.HomeScreenViewModel
import com.example.skillswap.viewmodel.LoginViewModel
import com.example.skillswap.viewmodel.MessagesViewModel
import com.example.skillswap.viewmodel.SignupViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SkillSwapTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {  innerPadding ->
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation(){
    var navController= rememberNavController()

    NavHost(navController = navController,
        startDestination = "signupAndLoginScreen"
        ){
        composable("signupAndLoginScreen"){
         SignupAndLoginScreen(navController)
        }
        composable("signupScreen"){
            val viewModel: SignupViewModel = viewModel()
            SignupScreen(viewModel,navController)

        }
        composable("loginScreen"){
            val viewModel: LoginViewModel = viewModel()
            LoginScreen(viewModel,navController)
        }

        composable("homeScreen"){
            val viewModel: HomeScreenViewModel = viewModel()
            SkillSwapHomeScreen(
                modifier = Modifier,
                viewModel = viewModel,
                navController = navController
            )

        }


        composable("messageListScreen"){
            MessagesListScreen()
        }
        composable("chatScreen"){
            MessageScreen()
        }

//        composable("messageScreenCard"){
//            MessagesListScreen()
//
//        }
        composable("matchedScreenCard"){
            MatchedScreenCard(navController, "DFAS",

            onSendRequest={},

            onMessage={


        })

        }

        composable("profileScreen"){
            ProfileScreen(
                onBack = {},
                onEdit = {}
            )
        }
        composable("notificationScreen"){
//            var item=mutableListOf<NotificationItem>()
//            NotificationListScreen(item)
        }
        composable("setting"){
            SettingsScreen(onBack =  {})
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SkillSwapTheme {
        Greeting("Android")
    }
}