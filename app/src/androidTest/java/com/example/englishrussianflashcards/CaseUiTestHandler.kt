package com.example.englishrussianflashcards

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice

/**
 * Created by Igor Aghibalov on 16.06.2024
 */
abstract class CaseUiTestHandler: ViewClickHandler,
                                  ViewAppearanceTester,
                                  CaseTestHandler,
                                  ScreenRotator {
    override fun clickOnView(viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId))
            .perform(ViewActions.click())
    }

    override fun checkViewAppearance(viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    override fun rotateScreen() {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).setOrientationLeft()
    }
}