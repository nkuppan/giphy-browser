import com.nkuppan.giphybrowser.buildsrc.Libs
import com.nkuppan.giphybrowser.buildsrc.Versions
import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

val apikeyProperties = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "keys.properties")))
}

android {
    compileSdk = Versions.compileSdk
    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        testInstrumentationRunner = Libs.AndroidX.Test.instrumentationRunner

        buildConfigField(
            "String",
            "GIPHY_API_KEY",
            ((apikeyProperties["GIPHY_API_KEY"] ?: "") as String)
        )
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}
dependencies {
    implementation(project(":domain"))

    implementation(Libs.AndroidX.appCompat)

    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.Kotlin.Coroutines.core)
    implementation(Libs.Kotlin.Coroutines.android)

    implementation(Libs.AndroidX.Paging.runtime)

    implementation(Libs.Google.Hilt.android)
    kapt(Libs.Google.Hilt.hiltCompiler)

    implementation(Libs.Square.okHttp)
    implementation(Libs.Square.Retrofit.retrofit)
    implementation(Libs.Square.Retrofit.gsonConverter)

    implementation(Libs.AndroidX.DataStore.preferences)

    testImplementation(Libs.Google.truth)
    testImplementation(Libs.AndroidX.Test.runner)
    androidTestImplementation(Libs.AndroidX.Test.JUnit.ktx)
    androidTestImplementation(Libs.AndroidX.Test.Espresso.core)
}