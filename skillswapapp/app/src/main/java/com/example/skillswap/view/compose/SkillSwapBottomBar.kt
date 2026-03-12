
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.skillswap.ui.theme.BrownPrimary
import com.example.skillswap.ui.theme.CreamSurface
import com.example.skillswap.ui.theme.DmSans
import com.example.skillswap.ui.theme.SkillTagBackground
import com.example.skillswap.ui.theme.TextHint

@Composable
fun SkillSwapBottomBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val navItems: List<Triple<String, ImageVector, String>> = listOf(
        Triple("Home", Icons.Outlined.Home, "homeScreen"),
        Triple("Chat", Icons.Outlined.ChatBubbleOutline, "messageListScreen"),
        Triple("Notifications", Icons.Outlined.NotificationsNone, "notificationScreen"),
        Triple("Profile", Icons.Outlined.AccountCircle, "profileScreen")
    )

    NavigationBar(
        containerColor = CreamSurface,
        tonalElevation = 10.dp
    ) {
        navItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.third,
                onClick = {
                    navController.navigate(item.third) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.second,
                        contentDescription = item.first
                    )
                },
                label = {
                    Text(
                        text = item.first,
                        fontFamily = DmSans,
                        fontWeight = FontWeight.Medium
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BrownPrimary,
                    selectedTextColor = BrownPrimary,
                    indicatorColor = SkillTagBackground,
                    unselectedIconColor = TextHint,
                    unselectedTextColor = TextHint
                )
            )
        }
    }
}
