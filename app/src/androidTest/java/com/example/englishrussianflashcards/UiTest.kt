package com.example.englishrussianflashcards

import android.view.View
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.example.englishrussianflashcards.presentation.MainActivity
import org.hamcrest.Matcher
import org.junit.Rule

/**
 * Created by Igor Aghibalov on 30.04.2025
 */
abstract class UiTest {

    @get: Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)


    abstract fun setUp()


    fun clickViewById(viewId: Int) {
        onView(withId(viewId)).perform(click())
    }


    fun clickViewByData(dataPosition: Int, dataMatcher: Matcher<Any>) {
        onData(dataMatcher)
            .atPosition(dataPosition)
            .perform(click())
    }


    fun testViewPresence(viewMatcher: Matcher<View>) {
        onView(viewMatcher).check(matches(isDisplayed()))
    }

    fun typeCharacters(viewIdToWriteIn: Int, typingCharacters: String) {
        onView(withId(viewIdToWriteIn)).perform(typeText(typingCharacters))
    }

    fun rotateDeviceToLeft() {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).setOrientationLeft()
    }

    fun rotateDeviceToNatural() {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).setOrientationNatural()
    }
}
