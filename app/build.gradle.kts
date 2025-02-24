plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt") // ✅ Required for Room & annotation processing
}

android {
    namespace = "com.example.oulumobilecomputing"
    compileSdk = 35 // ✅ Ensure compatibility with latest SDK

    defaultConfig {
        applicationId = "com.example.oulumobilecomputing"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true // ✅ Enable minification for performance
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false // Keep debugging easier
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5" // ✅ Latest Compose compiler
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // ✅ Core Android Dependencies
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")

    // ✅ Jetpack Compose BOM (Bill of Materials)
    implementation(platform("androidx.compose:compose-bom:2024.02.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // ✅ Navigation for Jetpack Compose
    implementation("androidx.navigation:navigation-compose:2.8.5")

    // ✅ Image Loading for Compose (Coil)
    implementation("io.coil-kt:coil-compose:2.5.0")

    // ✅ Room Database Dependencies
    implementation("androidx.room:room-runtime:2.6.1") // Core Room
    kapt("androidx.room:room-compiler:2.6.1") // Room Annotation Processor
    implementation("androidx.room:room-ktx:2.6.1") // Room Kotlin Extensions
    implementation("androidx.sqlite:sqlite:2.3.1")
    implementation("androidx.sqlite:sqlite-framework:2.3.1")

    // ✅ DataStore Preferences (for UserPreferences)
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // ✅ Coroutines Support
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // ✅ WorkManager for Background Tasks
    implementation("androidx.work:work-runtime-ktx:2.8.1")

    // ✅ CameraX (for Camera Functionality)
    implementation("androidx.camera:camera-core:1.3.2")
    implementation("androidx.camera:camera-camera2:1.3.2")
    implementation("androidx.camera:camera-lifecycle:1.3.2")

    // ✅ ExoPlayer (for Video Playback)
    implementation("androidx.media3:media3-exoplayer:1.3.1")
    implementation("androidx.media3:media3-ui:1.3.1")

    // ✅ Accompanist Permissions (Better Runtime Permission Handling)
    implementation("com.google.accompanist:accompanist-permissions:0.31.3-beta")

    // ✅ Testing Dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.02.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    // ✅ Debugging Tools for Compose
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}