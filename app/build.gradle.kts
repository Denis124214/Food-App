import android.databinding.tool.writer.ViewBinding

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.foodapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    val nav_version = "2.7.5"
    val lifecycle_version = "2.6.2"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // navigate component
    implementation ("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation ("androidx.navigation:navigation-ui-ktx:$nav_version")

    //intuit (облегчают создание более адаптивного и кросс-платформенного пользовательского интерфейса в приложениях для Android.)
    // 1 spd = 1 dp
    implementation ("com.intuit.sdp:sdp-android:1.1.0") // предоставляет возможность использования единиц измерения, называемых Scalable DP (SDP), которые автоматически масштабируются в зависимости от плотности пикселей (DP - Density-independent Pixels). Это позволяет более гибко настраивать размеры элементов в различных экранах.
    implementation ("com.intuit.ssp:ssp-android:1.1.0") // предоставляет аналогичный механизм для размеров текста. Она использует Scalable SP (SSP), что позволяет легко адаптировать размеры текста под разные размеры экранов.

    //gif
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.17") // предоставляет поддержку для работы с изображениями в формате GIF на платформе Android

    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // glide
    implementation ("com.github.bumptech.glide:glide:4.12.0") // Ее основное назначение - упростить и оптимизировать процесс работы с изображениями, обеспечивая эффективную загрузку, кэширование и отображение изображений.

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")





    // Gson - это библиотека для работы с форматом JSON в Java и Android. Эта зависимость добавляет Gson в ваш проек
    // it should learn (previous project)
    // implementation ("com.google.android.material:material:1.12.0-alpha02") // добавляет Material Design компоненты от Google в ваш проект Android
}