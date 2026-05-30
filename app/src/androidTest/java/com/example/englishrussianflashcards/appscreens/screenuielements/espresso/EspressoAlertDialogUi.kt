package com.example.englishrussianflashcards.appscreens.screenuielements.espresso

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.englishrussianflashcards.appscreens.screenuielements.DialogUi
import org.hamcrest.Matcher

/**
 * Created by Igor Aghibalov on 12.10.2025
 */
class EspressoAlertDialogUi(private val dialogMatcher: Matcher<View>,
                            private val dismissButtonMatcher: Matcher<View>): DialogUi {

    override fun isDisplayed() {
        onView(dialogMatcher)
            .inRoot(isDialog())
            .check(matches(ViewMatchers.isDisplayed()))
    }

    override fun hide() {
        onView(dismissButtonMatcher)
            .inRoot(isDialog())
            .perform(click())
    }
}