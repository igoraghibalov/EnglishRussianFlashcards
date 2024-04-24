package com.example.englishrussianflashcards

import android.app.Application
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Igor Aghibalov on 22.04.2024
 */
@RunWith(AndroidJUnit4::class)
class MainScreenUiTester: UiTester {

    private lateinit var mainActivityScenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        val applicationContext = ApplicationProvider.getApplicationContext<Application>()
        val intent = Intent(applicationContext, MainActivity::class.java)
        mainActivityScenario = ActivityScenario.launch<MainActivity>(intent)
    }


    override fun clickOnView(viewId: Int) {
        onView(withId(viewId)).perform(click())
    }


    override fun rotateScreen() {
        TODO("Not yet implemented")
    }


    override fun checkViewAppearance(viewId: Int) {
        onView(withId(viewId)).check(matches(isDisplayed()))
    }

    //TODO: add quit button click test
    @Test
    fun testCardCreationFragmentInflationOnNewButtonClick() {
        clickOnView(R.id.new_button)
        checkViewAppearance(R.id.word_typing_text_view)
    }
}