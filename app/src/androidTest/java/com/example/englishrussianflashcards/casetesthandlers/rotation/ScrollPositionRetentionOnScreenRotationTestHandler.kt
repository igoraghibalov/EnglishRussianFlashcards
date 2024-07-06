package com.example.englishrussianflashcards.casetesthandlers.rotation

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
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.englishrussianflashcards.CaseUiTestHandler
import org.hamcrest.Matcher
import org.hamcrest.Matchers

/**
 * Created by Igor Aghibalov on 29.06.2024
 */

class ScrollPositionRetentionOnScreenRotationTestHandler<T: RecyclerView.ViewHolder>(private val recyclerViewId: Int,
                                                         private val itemChildIdInPositionToCompare: Int,
                                                         private val applicationContext: Context)
    : CaseUiTestHandler() {

    override fun handleCaseTest() {
        val recyclerViewItem: CardView
        val itemText: String
        val lastVisibleItemPosition = getLastVisibleRecyclerViewItemPosition()
        val positionToScroll = lastVisibleItemPosition + 1

        scrollRecyclerViewThroughOneItem(lastVisibleItemPosition)
        recyclerViewItem = getRecyclerViewItemAtPosition(positionToScroll)
        itemText = recyclerViewItem.findViewById<TextView>(itemChildIdInPositionToCompare).text as String
        rotateScreen()
        onView(withText(itemText))
            .check(matches(isDisplayed()))
    }


    fun scrollRecyclerViewThroughOneItem(lastVisibleItemPosition: Int) {
        val recyclerViewInteraction: ViewInteraction
        val positionToScroll: Int
        val scrollingDownThroughOneItemAction: ViewAction

        recyclerViewInteraction = onView(ViewMatchers.withId(recyclerViewId))
        positionToScroll = lastVisibleItemPosition + 1
        scrollingDownThroughOneItemAction = RecyclerViewActions.scrollToPosition<T>(positionToScroll)
        recyclerViewInteraction.perform(scrollingDownThroughOneItemAction)
    }


    fun getRecyclerViewItemAtPosition(itemPosition: Int): CardView {
        var viewItem = CardView(applicationContext)
        val recyclerViewInteraction = onView(ViewMatchers.withId(recyclerViewId))

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
                val recyclerView = view as RecyclerView
                val recyclerViewLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                viewItem = recyclerViewLayoutManager.findViewByPosition(itemPosition) as CardView
            }
        }

        recyclerViewInteraction.perform(recyclerViewItemAtPositionExtraction)
        return viewItem
    }


    fun getLastVisibleRecyclerViewItemPosition(): Int {
        var lastVisibleItemPosition: Int = 0
        val recyclerViewInteraction = onView(ViewMatchers.withId(recyclerViewId))

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
                val recyclerView = view as RecyclerView
                val recyclerViewLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                lastVisibleItemPosition = recyclerViewLayoutManager.findLastVisibleItemPosition()
            }
        }

        recyclerViewInteraction.perform(lastVisibleItemPositionExtraction)
        return lastVisibleItemPosition
    }
}