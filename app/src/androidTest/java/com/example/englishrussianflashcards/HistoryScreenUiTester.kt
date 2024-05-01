package com.example.englishrussianflashcards

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.Test

/**
 * Created by Igor Aghibalov on 01.05.2024
 */


class HistoryScreenUiTester: UiTester() {

    override fun setup() {
        super.setup()
        setupProcessDeathTestEnvironment()
        testFragmentInflation(R.id.history_button, R.id.card_history_recycler_view)
    }


    @Test
    fun testCardFragmentInflationOnHistoryListItemClick() {
        onView(withId(R.id.card_history_recycler_view)).perform(click())
        onData(allOf(instanceOf(String::class.java))).atPosition(0).perform(click())
        checkViewAppearance(R.id.flashcard_front_side_view)
    }
}