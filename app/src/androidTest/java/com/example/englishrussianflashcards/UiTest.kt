package com.example.englishrussianflashcards

import android.view.View
import android.widget.TextView
import androidx.test.espresso.DataInteraction
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Root
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.example.englishrussianflashcards.presentation.MainActivity
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule

/**
 * Created by Igor Aghibalov on 30.04.2025
 */
abstract class UiTest {

    @get: Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    abstract fun setUp()


    fun clickViewById(viewId: Int) {
        onView(withId(viewId)).perform(click())
    }


    fun clickViewByData(dataPosition: Int, dataMatcher: Matcher<*>) {
        onData(dataMatcher)
            .atPosition(dataPosition)
            .perform(click())
    }


    fun clickViewByDataWithinRoot(rootMatcher: Matcher<Root>, dataMatcher: Matcher<*>, dataPosition: Int) {
        onData(dataMatcher)
            .inRoot(rootMatcher)
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


    fun extractTextFromDropdownMenuItem(itemPosition: Int,
                                        dropDownMenuRootMatcher: Matcher<Root>,
                                        dataMatcher: Matcher<*>): String {
        var text: String = ""

        val textExtractionAction = object: ViewAction {

            override fun getDescription(): String? = "Text extraction from dropdown menu item"

            override fun getConstraints(): Matcher<View?>? = isAssignableFrom(TextView::class.java)

            override fun perform(uiController: UiController?, view: View?) {
                text = (view as TextView).text.toString()
            }
        }

        onData(dataMatcher)
            .inRoot(dropDownMenuRootMatcher)
            .atPosition(itemPosition)
            .perform(textExtractionAction).perform(click())
        return text
    }


    fun clearText(viewId: Int) {
        onView(withId(viewId)).perform(clearText())
    }
}
