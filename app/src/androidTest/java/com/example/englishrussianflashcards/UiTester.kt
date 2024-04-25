package com.example.englishrussianflashcards

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers

/**
 * Created by Igor Aghibalov on 22.04.2024
 */
abstract class UiTester {

    private lateinit var mainActivityScenario: ActivityScenario<MainActivity>

    fun clickOnView(viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId))
            .perform(ViewActions.click())
    }

    abstract fun rotateScreen()

    fun checkViewAppearance(viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun testFragmentInflation(baseLayoutViewId: Int, inflatedFragmentViewId: Int) {
        clickOnView(baseLayoutViewId)
        checkViewAppearance(inflatedFragmentViewId)
        mainActivityScenario.recreate()
    }
}