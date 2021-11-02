package com.example.playwithcompose.ui

sealed class Screen(val route: String) {
    object StarsList : Screen(route = "stars_screen")
    object StarDetails : Screen(route = "star_details")
}