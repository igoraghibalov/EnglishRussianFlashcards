package com.example.englishrussianflashcards.casetesthandlers.rotation

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.englishrussianflashcards.CardWordExtractor
import com.example.englishrussianflashcards.CaseUiTestHandler
import com.example.englishrussianflashcards.TextExtractor

/**
 * Created by Igor Aghibalov on 04.07.2024
 */
class CardRetentionOnScreenRotationTestHandler(private val cardWordExtractor: TextExtractor)
    : CaseUiTestHandler() {


    override fun handleCaseTest() {
        val initialCardWord: String = cardWordExtractor.getText()
        rotateScreen()
        onView(withText(initialCardWord))
            .check(matches(isDisplayed()))
    }
}