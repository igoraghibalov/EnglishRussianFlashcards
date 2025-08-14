package com.example.englishrussianflashcards.testers

import com.example.englishrussianflashcards.R
import com.example.englishrussianflashcards.casetesthandlers.rotation.CardCreationFragmentOnScreenRotationTestHandler
import com.example.englishrussianflashcards.casetesthandlers.inflation.CardDemoDialogFragmentInflationTestHandler
import com.example.englishrussianflashcards.casetesthandlers.itemselection.CardElementsSelectionTestHandler
import org.junit.Test

/**
 * Created by Igor Aghibalov on 29.04.2024
 */
class CardCreationScreenUiTester: UiTester() {
    private lateinit var defaultTypingText: String

    override fun setupTestEnvironment() {
        super.setupTestEnvironment()
        inflationOnClickTester.clickOnView(R.id.create_card_button)
        inflationOnClickTester.checkViewAppearance(R.id.word_typing_text_view)
        defaultTypingText = applicationContext.resources.getString(R.string.default_typing_char_sequence)
    }

    @Test
    fun testCardElementsSelection() {
        testCase(caseTestHandler = CardElementsSelectionTestHandler(applicationContext))
    }


    @Test
    fun testCardDemoDialogFragmentInflationOnCreateButtonClick() {
        testCase(caseTestHandler = CardDemoDialogFragmentInflationTestHandler(defaultTypingText))
    }


    @Test
    fun testCardCreationFragmentDataRetentionAfterScreenRotation() {
        testCase(caseTestHandler = CardCreationFragmentOnScreenRotationTestHandler(defaultTypingText))
    }
}