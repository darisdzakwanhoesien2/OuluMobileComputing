package com.example.oulumobilecomputing.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.oulumobilecomputing.ui.views.ViewA
import com.example.oulumobilecomputing.ui.views.ViewB

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = "view_a") {
        composable("view_a") { ViewA(navController) }
        composable("view_b") { ViewB(navController) }
    }
}

