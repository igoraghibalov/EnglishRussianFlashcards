package com.example.englishrussianflashcards.screenuielements.espresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.englishrussianflashcards.screenuielements.DialogUi

/**
 * Created by Igor Aghibalov on 12.10.2025
 */
class EspressoAlertDialogUi(private val dialogMessage: String,
                            private val dismissButtonText: String): DialogUi {

    override fun isDisplayed() {
        onView(withText(dialogMessage))
            .inRoot(isDialog())
            .check(matches(ViewMatchers.isDisplayed()))
    }

    override fun hide() {
        onView(withText(dismissButtonText))
            .inRoot(isDialog())
            .perform(click())
    }
}