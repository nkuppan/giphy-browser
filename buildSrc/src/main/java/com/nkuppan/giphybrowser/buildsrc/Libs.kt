package com.nkuppan.giphybrowser.buildsrc

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"

    object Glide {
        const val core = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin.kotlin}"
        const val gradlePlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.kotlin}"

        object Coroutines {
            const val core =
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
            const val android =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
            const val test =
                "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.coroutines}"
        }
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"
        const val recyclerView =
            "androidx.recyclerview:recyclerview:${Versions.AndroidX.recyclerView}"
        const val swipeRefreshLayout =
            "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.AndroidX.swipeRefreshLayout}"

        object Core {
            const val ktx = "androidx.core:core-ktx:${Versions.AndroidX.core}"
            const val test = "androidx.arch.core:core-testing:${Versions.AndroidX.coreTest}"
        }

        object Activity {
            const val ktx = "androidx.activity:activity-ktx:${Versions.AndroidX.activity}"
        }

        object Fragment {
            const val ktx = "androidx.fragment:fragment-ktx:${Versions.AndroidX.fragment}"
            const val test = "androidx.fragment:fragment-testing:${Versions.AndroidX.fragment}"
        }

        object Lifecycle {
            const val viewModelKtx =
                "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycle}"
            const val liveDataKtx =
                "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidX.lifecycle}"
        }

        object DataStore {
            const val preferences =
                "androidx.datastore:datastore-preferences:${Versions.AndroidX.dataStore}"
        }

        object Room {
            const val runtime = "androidx.room:room-runtime:${Versions.AndroidX.room}"
            const val compiler = "androidx.room:room-compiler:${Versions.AndroidX.room}"
            const val ktx = "androidx.room:room-ktx:${Versions.AndroidX.room}"
        }

        object Paging {
            const val runtime = "androidx.paging:paging-runtime:${Versions.AndroidX.paging}"
        }

        object Navigation {
            const val uiKtx =
                "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.navigation}"
            const val fragmentKtx =
                "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.navigation}"
            const val safeArgsGradlePlugin =
                "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.AndroidX.navigation}"

            const val test = "androidx.navigation:navigation-testing:${Versions.AndroidX.navigation}"
        }

        object Test {
            const val runner = "androidx.test:runner:${Versions.AndroidX.Test.runner}"
            const val rules = "androidx.test:rules:${Versions.AndroidX.Test.rules}"
            const val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

            object Core {
                const val ktx = "androidx.test:core-ktx:${Versions.AndroidX.Test.core}"
            }

            object JUnit {
                const val core = "junit:junit:${Versions.AndroidX.Test.jUnit}"
                const val ktx = "androidx.test.ext:junit-ktx:${Versions.AndroidX.Test.jUnit}"
            }

            object Espresso {
                const val core =
                    "androidx.test.espresso:espresso-core:${Versions.AndroidX.Test.espresso}"
                const val contrib =
                    "com.android.support.test.espresso:espresso-contrib:${Versions.AndroidX.Test.espresso}"
            }
        }
    }

    object Google {
        const val material = "com.google.android.material:material:${Versions.Google.material}"
        const val truth = "com.google.truth:truth:${Versions.Google.truth}"

        object Hilt {
            const val android = "com.google.dagger:hilt-android:${Versions.Google.hilt}"
            const val hiltCompiler =
                "com.google.dagger:hilt-android-compiler:${Versions.Google.hilt}"
            const val androidGradlePlugin =
                "com.google.dagger:hilt-android-gradle-plugin:${Versions.Google.hilt}"
            const val test = "com.google.dagger:hilt-android-testing:${Versions.Google.hilt}"
        }
    }

    object Square {
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.Square.okHttp}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.Square.okHttp}"

        object Retrofit {
            const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Square.retrofit}"
            const val gsonConverter =
                "com.squareup.retrofit2:converter-gson:${Versions.Square.retrofit}"
        }
    }

    object Mockito {
        const val core = "org.mockito:mockito-core:${Versions.Mockito.core}"
        const val ktx = "org.mockito.kotlin:mockito-kotlin:${Versions.Mockito.ktx}"
    }
}
