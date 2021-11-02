package com.example.playwithcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.playwithcompose.ui.starDetails.StarDetailsScreen
import com.example.playwithcompose.ui.starsList.StarsListScreen
import com.example.playwithcompose.ui.theme.PlayWithComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayWithComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MyApp()
                }
            }
        }
    }
}


@Composable
fun MyApp() {
  Navigation()
}

const val KEY_STAR_ID = "starId"

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.StarsList.route
    ) {
        composable(route = Screen.StarsList.route) {
            StarsListScreen(navController = navController)
        }

        composable(
            route = Screen.StarDetails.route + "/{$KEY_STAR_ID}",
            arguments = listOf(
                navArgument(KEY_STAR_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            StarDetailsScreen()
        }
    }
}


