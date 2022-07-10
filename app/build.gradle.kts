import com.nkuppan.giphybrowser.buildsrc.Libs
import com.nkuppan.giphybrowser.buildsrc.Versions

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {

        applicationId = "com.nkuppan.giphybrowser"

        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk

        versionCode = Versions.versionCode
        versionName = Versions.versionName

        vectorDrawables {
            useSupportLibrary = true
        }

        testInstrumentationRunner = Libs.AndroidX.Test.instrumentationRunner
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        // Treat all Kotlin warnings as errors (disabled by default)
        allWarningsAsErrors = properties["warningsAsErrors"] as? Boolean ?: false

        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn",
            // Enable experimental coroutines APIs, including Flow
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
            "-opt-in=kotlin.Experimental"
        )
        
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(Libs.Glide.core)
    kapt(Libs.Glide.compiler)

    implementation(Libs.AndroidX.Paging.runtime)

    implementation(Libs.Google.Hilt.android)
    kapt(Libs.Google.Hilt.hiltCompiler)

    implementation(Libs.Square.okHttp)
    implementation(Libs.Square.Retrofit.retrofit)
    implementation(Libs.Square.Retrofit.gsonConverter)

    implementation(Libs.AndroidX.DataStore.preferences)

    /**
     * Android Testing Related Library
     */
    debugImplementation(Libs.AndroidX.Fragment.test)

    androidTestImplementation(Libs.AndroidX.Test.JUnit.ktx)
    androidTestImplementation(Libs.AndroidX.Test.Espresso.core)
    androidTestImplementation(Libs.AndroidX.Test.Espresso.contrib)
    androidTestImplementation(Libs.AndroidX.Navigation.test)
    androidTestImplementation(Libs.AndroidX.Test.runner)
    androidTestImplementation(Libs.AndroidX.Test.rules)
    androidTestImplementation(Libs.AndroidX.Core.test)
    androidTestImplementation(Libs.Google.truth)

    //Android JUnit test cases for API
    testImplementation(Libs.Square.mockWebServer)
    //for unit testing
    testImplementation(Libs.Google.truth)
    testImplementation(Libs.AndroidX.Test.JUnit.core)
    testImplementation(Libs.AndroidX.Core.test)
    testImplementation(Libs.Kotlin.Coroutines.test)
    testImplementation(Libs.Mockito.core)
    testImplementation(Libs.Mockito.ktx)
}