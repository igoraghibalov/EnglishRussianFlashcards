plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

ksp {
    val isTestTask = project.gradle.startParameter.taskNames.any {
        it.contains("Test", ignoreCase = true)
    }

    if (isTestTask)
        arg("dagger.hilt.disableModulesHaveInstallInCheck", "true")
}

android {
    namespace = "com.example.englishrussianflashcards"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.englishrussianflashcards"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
//        compose = true
    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.5.1"
//    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":createcard"))
    implementation(project(":createcard:presentation"))
    implementation(project(":customviews"))

    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.room.runtime.v261)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.core.ktx.v1120)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.appcompat.v161)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.material)
    implementation(libs.androidx.espresso.contrib)
    implementation(libs.androidx.uiautomator)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.hilt.android)

    ksp(libs.hilt.android.compiler)
    ksp(libs.androidx.room.compiler)

    testImplementation(project(":app"))
    testImplementation(libs.androidx.room.testing)
    testImplementation (libs.kotlinx.coroutines.test)
    testImplementation(libs.junit)
    testImplementation(libs.hilt.android.testing)
    testImplementation (libs.androidx.core.testing)

    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)
    androidTestImplementation(libs.hilt.android.testing)

    kspAndroidTest(libs.hilt.android.compiler)

    kspTest(libs.hilt.android.compiler)
}