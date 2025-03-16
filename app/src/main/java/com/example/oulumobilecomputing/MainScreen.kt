package com.example.oulumobilecomputing

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, username: String) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Home") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("Welcome, $username!", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            when (username.lowercase()) {
                "alice" -> Text("Alice, enjoy your vibrant theme!")
                "bob" -> Text("Bob, welcome to your calm interface.")
                "charlie" -> Text("Charlie, get ready for an energetic experience!")
                else -> Text("Welcome to the app!")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navController.navigate("login") }) {
                Text("Logout")
            }
        }
    }
}
