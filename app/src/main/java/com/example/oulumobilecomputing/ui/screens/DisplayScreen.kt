package com.example.oulumobilecomputing.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.oulumobilecomputing.viewmodel.ProfileViewModel

@Composable
fun DisplayScreen() {
    val context = LocalContext.current
    val viewModel: ProfileViewModel = viewModel(factory = ProfileViewModelFactory(context))

    var userData by remember { mutableStateOf<UserEntity?>(null) }

    LaunchedEffect(Unit) {
        viewModel.getUserData { userData = it }
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        userData?.let {
            Text("Name: ${it.name}")
            Image(painter = rememberAsyncImagePainter(it.imagePath), contentDescription = "Stored Image", modifier = Modifier.size(150.dp))
        }
    }
}
