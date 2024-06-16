package com.example.englishrussianflashcards

import android.app.Application
import android.content.Context
import android.os.Build

/**
 * Created by Igor Aghibalov on 16.06.2024
 */
class ProcessDeathTestEnvironmentSetuper(private val applicationContext: Context): TestEnvironmentSetuper {

    override fun setupTestEnvironment() {
        val application = applicationContext as Application
        val isSdkPrePie = application.applicationInfo.targetSdkVersion < Build.VERSION_CODES.P
        val processTerminator = EnglishRussianFlashcardsApplicationProcessTerminator()
        val mainActivityLifecycleObserver = MainActivityLifecycleObserver(isSdkPrePie, processTerminator)
        application.registerActivityLifecycleCallbacks(mainActivityLifecycleObserver)
    }
}