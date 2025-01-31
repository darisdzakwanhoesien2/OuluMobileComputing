package com.example.oulumobilecomputing.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewC(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("View C - Profile Setup") })
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it).padding(16.dp)
        ) {
            Text("This is View C", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("view_d") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go to View D")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.popBackStack("view_a", inclusive = false) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back to View A")
            }
        }
    }
}
