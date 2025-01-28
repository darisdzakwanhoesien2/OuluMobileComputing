package com.example.oulumobilecomputing

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavBackStackEntry
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class) // Suppresses the experimental API warning
@Composable
fun ViewC(navController: NavHostController) {
    val backStackEntry: NavBackStackEntry = remember { navController.previousBackStackEntry!! }
    val username = backStackEntry.savedStateHandle.get<String>("username") ?: "Default User"
    val imageUri = backStackEntry.savedStateHandle.get<String>("imageUri")

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Profile Summary") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("Username: $username")

            Spacer(modifier = Modifier.height(16.dp))

            imageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(Uri.parse(it)),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.popBackStack("ViewA", inclusive = false) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back to View A")
            }
        }
    }
}
