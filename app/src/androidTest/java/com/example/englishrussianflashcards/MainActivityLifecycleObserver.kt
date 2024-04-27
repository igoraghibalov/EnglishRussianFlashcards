package com.example.englishrussianflashcards

import android.app.Activity
import android.os.Bundle

/**
 * Created by Igor Aghibalov on 26.04.2024
 */
class MainActivityLifecycleObserver(private val isSdkPrePie: Boolean,
                                    private val processTerminator: ProcessTerminator): DefaultActivityLifecycleObserver() {


    override fun onActivityPostStopped(activity: Activity) {

        if (isSdkPrePie) {
            processTerminator.terminateProcess()
        }
    }

    override fun onActivityPostSaveInstanceState(activity: Activity, outState: Bundle) {

        if (!isSdkPrePie) {
            processTerminator.terminateProcess()
        }
    }
}