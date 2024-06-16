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
abstract class UiTester: TestEnvironmentSetuper, CaseTester, ScreenRotator {

    protected lateinit var mainActivityScenario: ActivityScenario<MainActivity>
    protected val applicationContext: Context = ApplicationProvider.getApplicationContext<Application>()

    @Before
    override fun setupTestEnvironment() {
        mainActivityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    override fun testCase(caseTestHandler: CaseTestHandler) {
        caseTestHandler.handleCaseTest()
    }


    override fun rotateScreen() {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).setOrientationLeft()
    }
}
