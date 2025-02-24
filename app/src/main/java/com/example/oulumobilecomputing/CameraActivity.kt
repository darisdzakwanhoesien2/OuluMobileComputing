package com.example.oulumobilecomputing

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import java.io.File

class CameraActivity : ComponentActivity() {

    private lateinit var imageView: ImageView
    private lateinit var photoFile: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Fix: Reference the correct layout file
        setContentView(R.layout.activity_camera)

        // ✅ Fix: Ensure `activity_camera.xml` exists in `res/layout/`
        imageView = findViewById(R.id.imageView)

        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = File.createTempFile("photo_", ".jpg", externalCacheDir)
        val photoURI: Uri = FileProvider.getUriForFile(
            this, "com.example.oulumobilecomputing.fileprovider", photoFile
        )
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)

        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                imageView.setImageURI(Uri.fromFile(photoFile))
            }
        }.launch(takePictureIntent)
    }
}