package com.alirahimi.facebooksample.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.alirahimi.facebooksample.screens.Destination

@Composable
fun FacebookBottomNavigation(navController: NavController, onDrawerIconClick: () -> Unit) {

    BottomNavigation {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry.value?.destination

        BottomNavigationItem(
            selected = currentDestination?.route == Destination.Home.route,
            onClick = {
                navController.navigate(Destination.Home.route)
            },
            icon = {
                Icon(Icons.Default.Home, contentDescription = "")
            },
            label = { Text(text = Destination.Home.route) }
        )

        BottomNavigationItem(
            selected = currentDestination?.route == Destination.Notification.route,
            onClick = {
                navController.navigate(Destination.Notification.route)
            },
            icon = {
                Icon(Icons.Default.Home, contentDescription = "")
            },
            label = { Text(text = Destination.Notification.route) }
        )

        BottomNavigationItem(
            selected = false,
            onClick = onDrawerIconClick,
            icon = {
                Icon(Icons.Default.Menu, contentDescription = "")
            },
            label = { Text(text = "Menu") }
        )
    }
}