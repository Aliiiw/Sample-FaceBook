package com.alirahimi.facebooksample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.alirahimi.facebooksample.navigation.FacebookBottomNavigation
import com.alirahimi.facebooksample.screens.*
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
    val localContext = LocalContext.current


    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            FacebookBottomNavigation(
                navController = navController,
                onDrawerIconClick = onDrawerIconClick
            )
        },
        drawerContent = { NavigationDrawer(navController = navController) },

        ) { padding ->

        val standardModifier = Modifier
            .padding(bottom = padding.calculateBottomPadding())
            .background(Color(0xffcccccc))
        NavHost(navController = navController, startDestination = Destination.Home.route) {

            composable(Destination.Home.route) {
                HomeScreen(navController = navController, modifier = standardModifier)
            }

            composable(Destination.Notification.route) {
                NotificationScreen(navController = navController, modifier = standardModifier)
            }

            composable(
                Destination.Detail.route,
                deepLinks = listOf(navDeepLink {
                    uriPattern = "https://www.fblikeapp.com/{itemId}"
                })
            ) {
                val itemId = it.arguments?.getString("itemId")

                if (itemId == null)
                    Toast.makeText(localContext, "Id is Required", Toast.LENGTH_SHORT).show()
                else
                    ItemDetailScreen(itemId.toInt(), modifier = standardModifier)
            }
        }
    }
}