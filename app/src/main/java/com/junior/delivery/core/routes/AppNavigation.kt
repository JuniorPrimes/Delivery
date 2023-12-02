package com.junior.delivery.core.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.junior.delivery.home.presentation.view.HomeScreen
import com.junior.delivery.signin.presentation.view.SignInScreen
import com.junior.delivery.signup.presentation.view.SignUpScreen

val LocalNavController = compositionLocalOf<NavHostController> { error("No NavController found!") }
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = Routes.SignInScreen.route){
            composable(route = Routes.HomeScreen.route){
                HomeScreen()
            }
            composable(route = Routes.SignInScreen.route){
                SignInScreen()
            }
            composable(route = Routes.SignUpScreen.route){
                SignUpScreen()
            }
        }
    }
}