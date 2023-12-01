package com.junior.delivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.junior.delivery.core.routes.Routes
import com.junior.delivery.home.presentation.view.HomeScreen
import com.junior.delivery.home.presentation.viewmodel.HomeViewModel
import com.junior.delivery.ui.theme.DeliveryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeliveryTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {
                        composable(Routes.HomeScreen.route) {
                            HomeScreen(homeViewModel).invoke(id = 1, username = "username", navController = navController)
                        }
                    }
                }
            }
        }
    }
}
