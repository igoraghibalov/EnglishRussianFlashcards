package com.example.englishrussianflashcards.appscreens.screenuielements.espresso

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import com.example.englishrussianflashcards.appscreens.screenuielements.ClickableUi
import org.hamcrest.Matcher

/**
* Created by Igor Aghibalov on 04.10.2025
*/

class EspressoClickableUi(private val clickableViewMatcher: Matcher<View>): ClickableUi {

    override fun click() {
        onView(clickableViewMatcher).perform(ViewActions.click())
    }
}