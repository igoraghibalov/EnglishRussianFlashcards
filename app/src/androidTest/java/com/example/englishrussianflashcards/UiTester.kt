package com.example.englishrussianflashcards

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.Before

/**
 * Created by Igor Aghibalov on 22.04.2024
 */
abstract class UiTester {

    protected lateinit var mainActivityScenario: ActivityScenario<MainActivity>
    protected val applicationContext: Context = ApplicationProvider.getApplicationContext<Application>()

    @Before
    open fun setup() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        mainActivityScenario = ActivityScenario.launch<MainActivity>(intent)
    }

    fun setupProcessDeathTestEnvironment() {
        val application = applicationContext as Application
        val isSdkPrePie = application.applicationInfo.targetSdkVersion < Build.VERSION_CODES.P
        val processTerminator = EnglishRussianFlashcardsApplicationProcessTerminator()
        val mainActivityLifecycleObserver = MainActivityLifecycleObserver(isSdkPrePie, processTerminator)
        application.registerActivityLifecycleCallbacks(mainActivityLifecycleObserver)
    }

    fun clickOnView(viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId))
            .perform(ViewActions.click())
    }

    fun rotateScreen() {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).setOrientationLeft()
    }

    fun checkViewAppearance(viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    open fun testFragmentInflation(baseLayoutViewId: Int, inflatedFragmentViewId: Int) {
        clickOnView(baseLayoutViewId)
        checkViewAppearance(inflatedFragmentViewId)
    }
}