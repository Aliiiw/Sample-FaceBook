package com.alirahimi.facebooksample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alirahimi.facebooksample.navigation.FacebookBottomNavigation
import com.alirahimi.facebooksample.screens.Destination
import com.alirahimi.facebooksample.screens.HomeScreen
import com.alirahimi.facebooksample.screens.NavigationDrawer
import com.alirahimi.facebooksample.screens.NotificationScreen
import com.alirahimi.facebooksample.ui.theme.FaceBookSampleTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FaceBookSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()

                    FacebookScaffold(navController = navController)
                }
            }
        }
    }
}


@Composable
fun FacebookScaffold(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val onDrawerIconClick: () -> Unit = {
        scope.launch {
            scaffoldState.drawerState.open()
        }
    }


    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            FacebookBottomNavigation(
                navController = navController,
                onDrawerIconClick = onDrawerIconClick
            )
        },
        drawerContent = { NavigationDrawer(navController = navController) }
    ) {
        NavHost(navController = navController, startDestination = Destination.Home.route) {

            composable(Destination.Home.route) {
                HomeScreen(navController = navController)
            }

            composable(Destination.Notification.route) {
                NotificationScreen(navController = navController)
            }
        }
    }
}