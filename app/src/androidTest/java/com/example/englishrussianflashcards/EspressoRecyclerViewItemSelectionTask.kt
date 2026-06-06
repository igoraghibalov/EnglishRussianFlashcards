package com.example.englishrussianflashcards

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher

/**
 * Created by Igor Aghibalov on 23.03.2026
 */
class EspressoRecyclerViewItemSelectionTask<T: RecyclerView.ViewHolder>(private val recyclerViewMatcher: Matcher<View>,
                                                                        private val itemViewMatcher: Matcher<View>,
                                                                        private val itemViewAction: ViewAction)
    : RecyclerViewItemSelectionTask<T> {

    override fun run() {
        onView(recyclerViewMatcher)
            .perform(RecyclerViewActions.actionOnItem<T>(itemViewMatcher, itemViewAction))
    }
}


interface RecyclerViewItemSelectionTask<T: RecyclerView.ViewHolder>: ListItemSelectionTask