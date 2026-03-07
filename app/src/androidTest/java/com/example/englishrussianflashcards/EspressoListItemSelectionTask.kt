package com.example.englishrussianflashcards

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
import org.hamcrest.Matcher

/**
 * Created by Igor Aghibalov on 07.03.2026
 */
class EspressoListItemSelectionTask<T: Any>(private val itemDataMatcher: Matcher<T>,
                                            private val itemPosition: Int)
        : ListItemSelectionTask {


    override fun run() {
        onData(itemDataMatcher)
            .inRoot(isPlatformPopup())
            .atPosition(itemPosition)
            .perform(click())
    }
}
