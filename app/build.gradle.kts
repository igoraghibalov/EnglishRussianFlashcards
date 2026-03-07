import org.gradle.kotlin.dsl.invoke

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
    testImplementation(project(":app"))

    val roomVersion = "2.6.1"

    implementation("androidx.fragment:fragment-ktx:1.7.1")
    implementation("androidx.room:room-runtime:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    implementation(libs.androidx.room.ktx)
    testImplementation("androidx.room:room-testing:$roomVersion")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.test.espresso:espresso-contrib:3.5.1")
    implementation("androidx.test.uiautomator:uiautomator:2.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1-Beta")
    testImplementation (libs.kotlinx.coroutines.test)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    testImplementation("org.robolectric:robolectric:4.12.1")
    testImplementation ("androidx.arch.core:core-testing:2.2.0")

    implementation("com.google.dagger:hilt-android:2.58")
    ksp("com.google.dagger:hilt-android-compiler:2.58")

    androidTestImplementation("com.google.dagger:hilt-android-testing:2.58")
    kspAndroidTest("com.google.dagger:hilt-android-compiler:2.58")

    testImplementation("com.google.dagger:hilt-android-testing:2.58")
    kspTest("com.google.dagger:hilt-android-compiler:2.58")
}