package com.example.oulumobilecomputing.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class) // ✅ Fix: Acknowledge that Material3 API might change
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
            Button(onClick = {
                if (navController.currentDestination?.route == "view_b") { // ✅ Prevent unintended navigation errors
                    navController.popBackStack("view_a", inclusive = false)
                }
            }) {
                Text("Back to View A")
            }
        }
    }
}



