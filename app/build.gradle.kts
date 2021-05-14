plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {

    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.compose.playground"
        versionCode = 1
        versionName = "0.0.1"
        minSdk = 23
        targetSdk = 30
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta06"
    }

    flavorDimensions("default")
}

dependencies {

    implementation("androidx.activity:activity-ktx:1.2.3")

    /*** Compose Libraries ***/
    implementation("androidx.activity:activity-compose:1.3.0-alpha07")
    implementation("androidx.compose.ui:ui:1.0.0-beta06")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-beta06")
    implementation("androidx.compose.material:material:1.0.0-beta06")
    implementation("androidx.compose.foundation:foundation:1.0.0-beta06")
    implementation("androidx.compose.foundation:foundation-layout:1.0.0-beta06")

    implementation("com.google.accompanist:accompanist-pager:0.9.1")

    /*** MapBox SDK ***/
    implementation("com.mapbox.mapboxsdk:mapbox-android-sdk:9.6.1")
}
