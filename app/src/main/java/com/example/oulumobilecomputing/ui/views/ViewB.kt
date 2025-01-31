package com.example.oulumobilecomputing.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewB(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("View B") }) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it).padding(16.dp)
        ) {
            Text(text = "This is View B", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            // Navigation Buttons
            Button(onClick = { navController.navigate("view_c") }) {
                Text("Go to View C")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                if (navController.currentDestination?.route == "view_b") {
                    navController.popBackStack("view_a", inclusive = false)
                }
            }) {
                Text("Back to View A")
            }
        }
    }
}





