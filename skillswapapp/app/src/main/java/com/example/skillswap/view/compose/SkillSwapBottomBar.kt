package com.example.skillswap.view.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.skillswap.R
import com.example.skillswap.ui.theme.BrownPrimary
import com.example.skillswap.ui.theme.CreamSurface
import com.example.skillswap.ui.theme.DmSans
import com.example.skillswap.ui.theme.SkillTagBackground
import com.example.skillswap.ui.theme.TextHint

@Composable
fun SkillSwapBottomBar(navController: NavController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val route = backStackEntry?.destination?.route
    NavigationBar(containerColor = CreamSurface) {
        NavigationBarItem(
            selected = route == "homeScreen",
            onClick = { navController.navigate("homeScreen") },
            icon = { Icon(Icons.Outlined.Home, null) },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = route == "messageListScreen",
            onClick = { navController.navigate("messageListScreen") },
            icon = { Icon(Icons.Outlined.ChatBubbleOutline, null) },
            label = { Text("Chats") }
        )
        NavigationBarItem(
            selected = route == "notificationScreen",
            onClick = { navController.navigate("notificationScreen") },
            icon = { Icon(Icons.Outlined.NotificationsNone, null) },
            label = { Text("Alerts") }
        )
        NavigationBarItem(
            selected = route == "profileScreen",
            onClick = { navController.navigate("profileScreen") },
            icon = { Icon(Icons.Outlined.AccountCircle, null) },
            label = { Text("Profile") }
        )
    }
}
 