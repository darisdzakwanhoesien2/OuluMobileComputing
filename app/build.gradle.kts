plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // Ensure the kapt plugin is applied for annotation processing
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.example.oulumobilecomputing"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.oulumobilecomputing"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Room Dependencies
    val room_version = "2.5.2"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")  // Ensure kapt dependency is included
    implementation("androidx.room:room-ktx:$room_version")  // Kotlin extensions

    // Core Libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Jetpack Compose Libraries
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Navigation for Compose
    // Uncomment the following line if you wish to use the version catalog version
    // implementation(libs.androidx.navigation.compose)
    implementation("androidx.navigation:navigation-compose:2.8.5")
    implementation(libs.androidx.navigation.compose)

    // Image Loading for Compose (Coil)
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation(libs.androidx.ui.foundation)

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Notifications (Note: Check for duplicate core-ktx versions)
    implementation("androidx.core:core-ktx:1.12.0")

    // Sensors
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")

    // Testing Dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Debugging Tools
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
