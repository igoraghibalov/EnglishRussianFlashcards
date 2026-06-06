package com.example.englishrussianflashcards.appscreens.screenuielements.espresso

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import com.example.englishrussianflashcards.appscreens.screenuielements.TextUi
import org.hamcrest.Matcher

/**
 * Created by Igor Aghibalov on 11.10.2025
 */
class EspressoTextUi(private val textViewMatcher: Matcher<View>,
                     private val textMatcher: Matcher<View>): TextUi {

    override fun hasText() {
        onView(textViewMatcher).check(matches(textMatcher))
    }
}