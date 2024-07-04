package com.example.englishrussianflashcards.casetesthandlers.inflation

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.CaseUiTestHandler
import com.example.englishrussianflashcards.R
import com.example.englishrussianflashcards.TextExtractor
import org.junit.Assert
import org.junit.Assert.assertNotEquals

/**
 * Created by Igor Aghibalov on 04.07.2024
 */
class CardRemoveOnSwipeUpTestHandler(private val cardWordExtractor: TextExtractor)
    : CaseUiTestHandler() {


    override fun handleCaseTest() {
        val initialCardWord = cardWordExtractor.getText()
        onView(withId(R.id.card_front_side_view))
            .perform(swipeUp())
        val postSwipeCardWord = cardWordExtractor.getText()
        assertNotEquals(initialCardWord, postSwipeCardWord, "Card swipe up is failed")
    }


}