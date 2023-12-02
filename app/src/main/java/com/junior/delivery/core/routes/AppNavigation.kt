package com.junior.delivery.core.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.junior.delivery.home.presentation.view.HomeScreen
import com.junior.delivery.signin.presentation.SignInScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.SignInScreen.route){
        composable(route = Routes.HomeScreen.route){
            HomeScreen(navController)
        }
        composable(route = Routes.SignInScreen.route){
            SignInScreen(navController)
        }
    }
}