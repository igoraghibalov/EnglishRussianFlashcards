package com.example.englishrussianflashcards.casetesthandlers.rotation

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.CaseUiTestHandler
import com.example.englishrussianflashcards.R

/**
 * Created by Igor Aghibalov on 04.07.2024
 */
class CardRotationTestHandler: CaseUiTestHandler() {


    override fun handleCaseTest() {
        val cardViewInteraction = onView(withId(R.id.card_front_side_view))

        cardViewInteraction.perform(click())

        onView(withId(R.id.word_image_view))
            .check(matches(isDisplayed()))
    }
}