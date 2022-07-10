import com.nkuppan.giphybrowser.buildsrc.Libs
import com.nkuppan.giphybrowser.buildsrc.Versions

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Versions.compileSdk
    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        testInstrumentationRunner = Libs.AndroidX.Test.instrumentationRunner
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    api(Libs.Kotlin.stdlib)
    api(Libs.Kotlin.Coroutines.core)
    api(Libs.Kotlin.Coroutines.android)

    api(Libs.AndroidX.Core.ktx)
    api(Libs.AndroidX.appCompat)
    api(Libs.AndroidX.constraintLayout)
    api(Libs.AndroidX.recyclerView)
    api(Libs.AndroidX.swipeRefreshLayout)

    api(Libs.AndroidX.Activity.ktx)
    api(Libs.AndroidX.Fragment.ktx)

    api(Libs.AndroidX.Lifecycle.liveDataKtx)
    api(Libs.AndroidX.Lifecycle.viewModelKtx)

    api(Libs.AndroidX.Navigation.fragmentKtx)
    api(Libs.AndroidX.Navigation.uiKtx)

    api(Libs.Google.material)

    implementation(Libs.Glide.core)
    kapt(Libs.Glide.compiler)

    testImplementation(Libs.AndroidX.Test.runner)
    androidTestImplementation(Libs.AndroidX.Test.JUnit.ktx)
    androidTestImplementation(Libs.AndroidX.Test.Espresso.core)
}