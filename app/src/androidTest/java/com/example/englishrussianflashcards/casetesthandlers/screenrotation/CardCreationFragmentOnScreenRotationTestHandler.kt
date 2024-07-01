package com.example.englishrussianflashcards.casetesthandlers.screenrotation

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.englishrussianflashcards.CaseUiTestHandler
import com.example.englishrussianflashcards.R

/**
 * Created by Igor Aghibalov on 24.06.2024
 */
class CardCreationFragmentOnScreenRotationTestHandler(private val defaultTypingText: String)
    : CaseUiTestHandler() {


    override fun handleCaseTest() {
        onView(withId(R.id.word_typing_text_view))
            .perform(typeText(defaultTypingText))

        rotateScreen()

        onView(withId(R.id.word_typing_text_view))
            .check(matches(withText(defaultTypingText)))
    }

    override fun doAfterCaseTestHandling() {}
}