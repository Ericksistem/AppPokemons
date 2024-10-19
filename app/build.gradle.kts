plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    //Kapt and Hilt
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

    //Serialization
    kotlin("plugin.serialization")

    id("com.google.gms.google-services")

    id("com.google.firebase.crashlytics")

    id("com.google.firebase.firebase-perf")
}

android {
    namespace = "com.ds.pokeapi_kotlin_idigitalstudios"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ds.pokeapi_kotlin_idigitalstudios"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures{
        compose = true
    }
    composeOptions{
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    testOptions{
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
    implementation("com.google.android.gms:play-services-location:21.2.0")
    //Compose
    val composeBom = platform("androidx.compose:compose-bom:2024.02.00")
    implementation(composeBom)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-util")


    implementation("androidx.compose.material3:material3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    // Android Studio Preview support
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Full set of material icons
    implementation("androidx.compose.material:material-icons-extended")

    // Add window size utils
    implementation("androidx.compose.material3:material3-window-size-class")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    //MLKit
    implementation("com.google.mlkit:text-recognition:16.0.0")

    //Android and KTX
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    //Activity and Fragment
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.activity:activity-compose:1.8.2")

    //Room
    val room_version = "2.6.1"
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    //Performance Metrics
    implementation("androidx.metrics:metrics-performance:1.0.0-beta01")

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")

    //DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //Splash Screen
    implementation("androidx.core:core-splashscreen:1.1.0-alpha02")

    //Navigation With Hilt
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    //Networking and Serialization
    val ktorVersion = "2.3.6"
    implementation("io.ktor:ktor-client-android:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-client-auth:$ktorVersion")
    implementation("io.ktor:ktor-client-logging-jvm:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    //Tracing
    implementation("androidx.tracing:tracing-ktx:1.2.0")

    //Charts
    implementation("co.yml:ycharts:2.1.0")

    //Image
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("io.github.panpf.zoomimage:zoomimage-compose-coil:1.0.0")

    //Workers
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("androidx.hilt:hilt-work:1.2.0")
    kapt("androidx.hilt:hilt-compiler:1.2.0")

    //Lottie
    implementation("com.airbnb.android:lottie-compose:6.2.0")

    //Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //PullRefresh
    implementation("eu.bambooapps:compose-material3-pullrefresh:1.0.1")

    //Permissions
    implementation("com.google.accompanist:accompanist-permissions:0.33.2-alpha")

    //Camposer
    implementation("io.github.ujizin:camposer:0.3.2")

    //Drag
    implementation("com.dragselectcompose:dragselect:2.2.6")

    // Retrofit and serialization
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Para convertir JSON
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    // Coroutines (opcional, pero recomendado para manejo asíncrono)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.1.0"))
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-perf-ktx")

    //Splash
    implementation("androidx.core:core-splashscreen:1.0.1")

    // recycleView
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation ("com.squareup.picasso:picasso:2.71828")
}

kapt {
    correctErrorTypes = true
}