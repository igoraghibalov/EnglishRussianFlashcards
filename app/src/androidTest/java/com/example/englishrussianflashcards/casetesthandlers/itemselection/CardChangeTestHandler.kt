package com.example.englishrussianflashcards.casetesthandlers.itemselection

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.englishrussianflashcards.CaseUiTestHandler
import com.example.englishrussianflashcards.R
import com.example.englishrussianflashcards.TextExtractor
import org.hamcrest.Matchers.not

/**
 * Created by Igor Aghibalov on 03.07.2024
 */
class CardChangeTestHandler(private val cardViewId: Int,
                            private val cardWordExtractor: TextExtractor): CaseUiTestHandler() {


    override fun handleCaseTest() {
        val initialCardWord: String = cardWordExtractor.getText()
        val cardViewInteraction = onView(withId(cardViewId))

        cardViewInteraction.perform(swipeLeft())

        onView(withText(initialCardWord))
            .check(matches(not(isDisplayed())))

        cardViewInteraction.perform(swipeRight())

        onView(withText(initialCardWord))
            .check(matches(isDisplayed()))
    }
}