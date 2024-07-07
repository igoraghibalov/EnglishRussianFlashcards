package com.example.englishrussianflashcards

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.example.englishrussianflashcards.testers.InflationOnClickTester
import com.example.englishrussianflashcards.testers.ViewAppearanceTester

/**
 * Created by Igor Aghibalov on 16.06.2024
 */
abstract class CaseUiTestHandler: InflationOnClickTester(),
                                  CaseTestHandler,
                                  ScreenRotator {

    override fun rotateScreen() {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).setOrientationLeft()
    }
}