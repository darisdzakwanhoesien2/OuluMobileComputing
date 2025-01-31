package com.example.oulumobilecomputing.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.oulumobilecomputing.ui.views.ViewA
import com.example.oulumobilecomputing.ui.views.ViewB
import com.example.oulumobilecomputing.ui.views.ViewC
import com.example.oulumobilecomputing.ui.views.ViewD
import com.example.oulumobilecomputing.ui.views.ViewE

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "view_a") {
        composable("view_a") { ViewA(navController) }
        composable("view_b") { ViewB(navController) }
        composable("view_c") { ViewC(navController) }
        composable("view_d") { ViewD(navController) }
        composable("view_e") { ViewE(navController) }
    }
}