package com.example.englishrussianflashcards.testers

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId

/**
 * Created by Igor Aghibalov on 07.07.2024
 */
open class BaseInflationOnClickTester: InflationOnClickTester {


    override fun clickOnView(viewId: Int) {
        onView(withId(viewId))
            .perform(click())
    }

    override fun checkViewAppearance(viewId: Int) {
        onView(withId(viewId))
            .check(matches(isDisplayed()))
    }
}