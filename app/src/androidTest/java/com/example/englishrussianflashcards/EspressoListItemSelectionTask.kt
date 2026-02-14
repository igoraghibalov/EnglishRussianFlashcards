package com.example.englishrussianflashcards

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import org.hamcrest.Matcher

/**
 * Created by Igor Aghibalov on 14.02.2026
 */
class EspressoListItemSelectionTask(private val itemPosition: Int,
                                    private val itemMatcher: Matcher<String>): ListItemSelectionTask {

    override fun run() {
        onData(itemMatcher).atPosition(itemPosition).perform(click())
    }
}