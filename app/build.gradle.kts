plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.google.devtools.ksp)
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
    compileSdk = 37

    defaultConfig {
        applicationId = "com.example.englishrussianflashcards"
        minSdk = 24
        targetSdk = 37
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

    buildFeatures {
//        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
    }
}

dependencies {
    implementation(project(":createcard:presentation"))
    implementation(project(":customviews"))

    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.material)
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

    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.hilt.android.testing)

    kspAndroidTest(libs.hilt.android.compiler)

    kspTest(libs.hilt.android.compiler)
}