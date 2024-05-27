package com.example.englishrussianflashcards

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.englishrussianflashcards.presentation.CardHistoryViewHolder
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.Test

/**
 * Created by Igor Aghibalov on 01.05.2024
 */


class HistoryScreenUiTester: UiTester() {

    override fun setup() {
        super.setup()
        testFragmentInflation(R.id.history_button, R.id.card_history_recycler_view)
    }


    @Test
    fun testCardFragmentInflationOnHistoryListItemClick() {
        onView(withId(R.id.card_history_recycler_view)).perform(click())
        onData(allOf(instanceOf(String::class.java))).atPosition(0).perform(click())
        checkViewAppearance(R.id.card_front_side_view)
    }



    fun getRecyclerViewLastVisibleItemPosition(): Int {
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


    @Test
    fun testScrolledOnItemsRetentionAfterDeviceRotation() {
        val cardHistoryRecyclerViewInteraction = onView(withId(R.id.card_history_recycler_view))
        val lastVisibleItemPosition = getRecyclerViewLastVisibleItemPosition()
        val positionToScroll = lastVisibleItemPosition + 1
        val scrollingDownThroughOneItemAction = RecyclerViewActions.scrollToPosition<CardHistoryViewHolder>(positionToScroll)
        val recyclerViewItem: TextView
        val itemText: String
        cardHistoryRecyclerViewInteraction.perform(click())
        cardHistoryRecyclerViewInteraction.perform(scrollingDownThroughOneItemAction)
        recyclerViewItem = getRecyclerViewItemAtPosition(positionToScroll)
        itemText = recyclerViewItem.findViewById<TextView>(R.id.word_text_view).text as String
        rotateScreen()
        onView(withText(itemText)).check(matches(isDisplayed()))
    }



    @Test
    fun testScrolledOnItemsRetentionAfterProcessDeath() {
        val lastVisibleRecyclerViewItemPosition: Int
        val recyclerViewItem: TextView
        val recyclerViewItemText: String

        setupProcessDeathTestEnvironment()
        scrollRecyclerViewThroughOneItem()
        lastVisibleRecyclerViewItemPosition = getRecyclerViewLastVisibleItemPosition()
        recyclerViewItem = getRecyclerViewItemAtPosition(lastVisibleRecyclerViewItemPosition)
        recyclerViewItemText = recyclerViewItem.text.toString()
        mainActivityScenario.recreate()
        onView(withText(recyclerViewItemText)).check(matches(isDisplayed()))
    }


    fun getRecyclerViewItemAtPosition(itemPosition: Int): TextView {
        var viewItem = TextView(applicationContext)
        val cardHistoryRecyclerViewInteraction = onView(withId(R.id.card_history_recycler_view))

        val recyclerViewItemAtPositionExtraction = object: ViewAction {

                override fun getDescription(): String = "Get recycler view item at position"

                override fun getConstraints(): Matcher<View> {
                    val recyclerViewMatcher = allOf(isAssignableFrom(RecyclerView::class.java), isDisplayed())
                    return recyclerViewMatcher
                }


                override fun perform(uiController: UiController?, view: View?) {
                    val cardHistoryRecyclerView = view as RecyclerView
                    val cardHistoryRecyclerViewLayoutManager = cardHistoryRecyclerView.layoutManager as LinearLayoutManager
                    viewItem = cardHistoryRecyclerViewLayoutManager.findViewByPosition(itemPosition) as TextView
                }
            }

        cardHistoryRecyclerViewInteraction.perform(recyclerViewItemAtPositionExtraction)
        return viewItem
    }


    fun scrollRecyclerViewThroughOneItem() {
        val cardHistoryRecyclerViewInteraction: ViewInteraction
        val lastVisibleItemPosition: Int
        val positionToScroll: Int
        val scrollingDownThroughOneItemAction: ViewAction

        cardHistoryRecyclerViewInteraction = onView(withId(R.id.card_history_recycler_view))
        lastVisibleItemPosition = getRecyclerViewLastVisibleItemPosition()
        positionToScroll = lastVisibleItemPosition + 1
        scrollingDownThroughOneItemAction = RecyclerViewActions.scrollToPosition<CardHistoryViewHolder>(positionToScroll)
        cardHistoryRecyclerViewInteraction.perform(scrollingDownThroughOneItemAction)
    }
}