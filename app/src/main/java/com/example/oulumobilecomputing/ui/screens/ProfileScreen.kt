package com.example.oulumobilecomputing.ui.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.oulumobilecomputing.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(onNavigateToDisplay: () -> Unit) {
    val context = LocalContext.current
    val viewModel: ProfileViewModel = viewModel(factory = ProfileViewModelFactory(context))

    var name by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        imageUri = uri
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        BasicTextField(value = name, onValueChange = { name = it }, modifier = Modifier.padding(16.dp))

        Button(onClick = { imagePicker.launch("image/*") }) {
            Text("Pick an Image")
        }

        imageUri?.let {
            Image(painter = rememberAsyncImagePainter(it), contentDescription = "Selected Image", modifier = Modifier.size(150.dp))
        }

        Button(onClick = {
            viewModel.saveUserData(name, imageUri) { 
                Toast.makeText(context, "Data Saved!", Toast.LENGTH_SHORT).show()
                onNavigateToDisplay()
            }
        }) {
            Text("Save and View")
        }
    }
}
