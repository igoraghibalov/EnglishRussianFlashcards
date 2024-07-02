package com.example.englishrussianflashcards.casetesthandlers.screenrotation

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.englishrussianflashcards.CaseUiTestHandler
import com.example.englishrussianflashcards.presentation.CardHistoryViewHolder
import org.hamcrest.Matcher
import org.hamcrest.Matchers

/**
 * Created by Igor Aghibalov on 29.06.2024
 */

class ScrollPositionRetentionOnScreenRotationTestHandler(private val recyclerViewId: Int,
                                                         private val itemChildIdInPositionToCompare: Int,
                                                         private val applicationContext: Context)
    : CaseUiTestHandler() {

    override fun handleCaseTest() {
        val recyclerViewItem: CardView
        val itemText: String
        val lastVisibleItemPosition = getRecyclerViewLastVisibleItemPosition()
        val positionToScroll = lastVisibleItemPosition + 1

        scrollRecyclerViewThroughOneItem(lastVisibleItemPosition)
        recyclerViewItem = getRecyclerViewItemAtPosition(positionToScroll)
        itemText = recyclerViewItem.findViewById<TextView>(itemChildIdInPositionToCompare).text as String
        rotateScreen()
        onView(ViewMatchers.withText(itemText))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    fun scrollRecyclerViewThroughOneItem(lastVisibleItemPosition: Int) {
        val cardHistoryRecyclerViewInteraction: ViewInteraction
        val positionToScroll: Int
        val scrollingDownThroughOneItemAction: ViewAction

        cardHistoryRecyclerViewInteraction = onView(ViewMatchers.withId(recyclerViewId))
        positionToScroll = lastVisibleItemPosition + 1
        scrollingDownThroughOneItemAction = RecyclerViewActions.scrollToPosition<CardHistoryViewHolder>(positionToScroll)
        cardHistoryRecyclerViewInteraction.perform(scrollingDownThroughOneItemAction)
    }


    fun getRecyclerViewItemAtPosition(itemPosition: Int): CardView {
        var viewItem = CardView(applicationContext)
        val cardHistoryRecyclerViewInteraction = onView(ViewMatchers.withId(recyclerViewId))

        val recyclerViewItemAtPositionExtraction = object: ViewAction {

            override fun getDescription(): String = "Get recycler view item at position"

            override fun getConstraints(): Matcher<View> {
                val recyclerViewMatcher = Matchers.allOf(
                    ViewMatchers.isAssignableFrom(RecyclerView::class.java),
                    ViewMatchers.isDisplayed()
                )
                return recyclerViewMatcher
            }


            override fun perform(uiController: UiController?, view: View?) {
                val cardHistoryRecyclerView = view as RecyclerView
                val cardHistoryRecyclerViewLayoutManager = cardHistoryRecyclerView.layoutManager as LinearLayoutManager
                viewItem = cardHistoryRecyclerViewLayoutManager.findViewByPosition(itemPosition) as CardView
            }
        }

        cardHistoryRecyclerViewInteraction.perform(recyclerViewItemAtPositionExtraction)
        return viewItem
    }


    fun getRecyclerViewLastVisibleItemPosition(): Int {
        var lastVisibleItemPosition: Int = 0
        val cardHistoryRecyclerViewInteraction = onView(ViewMatchers.withId(recyclerViewId))

        val lastVisibleItemPositionExtraction = object: ViewAction {

            override fun getDescription(): String = "Last visible item position extraction"

            override fun getConstraints(): Matcher<View> {
                val recyclerViewMatcher = Matchers.allOf(
                    ViewMatchers.isAssignableFrom(RecyclerView::class.java),
                    ViewMatchers.isDisplayed()
                )
                return recyclerViewMatcher
            }


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