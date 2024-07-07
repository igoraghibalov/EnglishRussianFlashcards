package com.example.englishrussianflashcards.testers

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.CardWordExtractor
import com.example.englishrussianflashcards.R
import com.example.englishrussianflashcards.TextExtractor
import com.example.englishrussianflashcards.casetesthandlers.inflation.CardRemoveOnSwipeUpTestHandler
import com.example.englishrussianflashcards.casetesthandlers.itemselection.CardChangeTestHandler
import com.example.englishrussianflashcards.casetesthandlers.rotation.CardRetentionOnScreenRotationTestHandler
import com.example.englishrussianflashcards.casetesthandlers.rotation.CardRotationTestHandler
import org.junit.Test

/**
 * Created by Igor Aghibalov on 05.05.2024
 */
class CardScreenUiTester: UiTester() {
    private lateinit var cardWordExtractor: TextExtractor

    override fun setupTestEnvironment() {
        super.setupTestEnvironment()
        onView(withId(R.id.continue_button)).perform(click())
        cardWordExtractor = CardWordExtractor(cardViewId = R.id.card_front_side_view,
                                              textOwnerChildViewId = R.id.english_word_text_view
        )
    }

    
    @Test
    fun testCardChangeOnSwipeLeftAndRight() {
        testCase(caseTestHandler = CardChangeTestHandler(R.id.card_front_side_view, cardWordExtractor))
    }

    @Test
    fun testCardRotationOnClick() {
        testCase(caseTestHandler = CardRotationTestHandler())
    }

    @Test
    fun testCardRetentionOnScreenRotation() {
        testCase(caseTestHandler = CardRetentionOnScreenRotationTestHandler(cardWordExtractor))
    }


    @Test
    fun testCardRemoveOnSwipeUp() {
        testCase(caseTestHandler = CardRemoveOnSwipeUpTestHandler(cardWordExtractor))
    }
}