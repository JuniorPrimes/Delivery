package com.junior.delivery.signin.presentation

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.junior.delivery.core.routes.Routes

@Composable
fun SignInScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Text(text = "xd", modifier = modifier.clickable { navController.navigate(Routes.HomeScreen.route) })
}