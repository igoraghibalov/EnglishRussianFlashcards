package com.example.englishrussianflashcards

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.Test

/**
 * Created by Igor Aghibalov on 01.05.2024
 */

//TODO: code process death test
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


    fun getCardHistoryRecyclerViewLastVisibleItemPosition(): Int {
        var lastVisibleItemPosition: Int = 0
        val cardHistoryRecyclerViewInteraction = onView(withId(R.id.card_history_recycler_view))

        val lastVisibleItemPositionExtraction = object: ViewAction {

                override fun getDescription(): String = "Last visible item position extraction"

                override fun getConstraints(): Matcher<View> {
                    val recyclerViewMatcher = allOf(isAssignableFrom(RecyclerView::class.java), isDisplayed())
                    return recyclerViewMatcher
                }

                //TODO: try to replace down-casting with polymorphism
                override fun perform(uiController: UiController?, view: View?) {
                    val cardHistoryRecyclerView = view as RecyclerView
                    val cardHistoryRecyclerViewLayoutManager = cardHistoryRecyclerView.layoutManager as LinearLayoutManager
                    lastVisibleItemPosition = cardHistoryRecyclerViewLayoutManager.findLastVisibleItemPosition()
                }
            }

        cardHistoryRecyclerViewInteraction.perform(lastVisibleItemPositionExtraction)
        return lastVisibleItemPosition
    }
}