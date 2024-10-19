// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false

    //Hilt Plugin
    id("com.google.dagger.hilt.android") version "2.48" apply false

    //Serialization
    kotlin("plugin.serialization") version "1.9.10" apply false

    //Google Services
    id("com.google.gms.google-services") version "4.3.15" apply false

    //Firebase
    id("com.google.firebase.crashlytics") version "2.9.9" apply false

    //Performance
    id("com.google.firebase.firebase-perf") version "1.4.2" apply false
}