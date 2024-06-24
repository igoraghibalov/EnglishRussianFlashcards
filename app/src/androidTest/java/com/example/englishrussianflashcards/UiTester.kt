package com.example.englishrussianflashcards

import android.app.Application
import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.example.englishrussianflashcards.presentation.MainActivity
import org.junit.Before

/**
 * Created by Igor Aghibalov on 22.04.2024
 */
abstract class UiTester: Tester() {

    protected lateinit var mainActivityScenario: ActivityScenario<MainActivity>
    protected lateinit var applicationContext: Context

    @Before
    override fun setupTestEnvironment() {
        applicationContext = ApplicationProvider.getApplicationContext<Application>()
        mainActivityScenario = ActivityScenario.launch(MainActivity::class.java)
    }
}
