package com.example.englishrussianflashcards

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.englishrussianflashcards.casetesthandlers.CardCreationFragmentOnProcessDeathTestHandler
import com.example.englishrussianflashcards.casetesthandlers.CardCreationFragmentOnScreenRotationTestHandler
import com.example.englishrussianflashcards.casetesthandlers.CardDemoDialogFragmentInflationTestHandler
import com.example.englishrussianflashcards.casetesthandlers.CardElementsSelectionTestHandler
import org.junit.Test

/**
 * Created by Igor Aghibalov on 29.04.2024
 */
class CardCreationScreenUiTester: UiTester() {
    private lateinit var defaultTypingText: String

    override fun setupTestEnvironment() {
        super.setupTestEnvironment()
        defaultTypingText = applicationContext.resources.getString(R.string.default_typing_char_sequence)
    }

    @Test
    fun testCardElementsSelection() {
        testCase(caseTestHandler = CardElementsSelectionTestHandler(applicationContext))
    }


    @Test
    fun testCardDemoDialogFragmentInflationOnCreateButtonClick() {
        testCase(caseTestHandler = CardDemoDialogFragmentInflationTestHandler())
    }


    @Test
    fun testCardCreationFragmentDataRetentionAfterScreenRotation() {
        testCase(caseTestHandler = CardCreationFragmentOnScreenRotationTestHandler(defaultTypingText))
    }


    @Test
    fun testCardCreationFragmentDataRetentionAfterProcessDeath() {
        testCase(caseTestHandler = CardCreationFragmentOnProcessDeathTestHandler(applicationContext,
                                                                                 mainActivityScenario,
                                                                                 defaultTypingText))
    }
}