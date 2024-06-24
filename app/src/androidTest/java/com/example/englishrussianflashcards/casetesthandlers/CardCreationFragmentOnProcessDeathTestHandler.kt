package com.example.englishrussianflashcards.casetesthandlers

import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.englishrussianflashcards.CaseUiTestHandler
import com.example.englishrussianflashcards.ProcessDeathTestEnvironmentSetuper
import com.example.englishrussianflashcards.R
import com.example.englishrussianflashcards.presentation.MainActivity

/**
 * Created by Igor Aghibalov on 24.06.2024
 */
class CardCreationFragmentOnProcessDeathTestHandler(private val applicationContext: Context,
                                                    private val mainActivityScenario: ActivityScenario<MainActivity>,
                                                    private val defaultTypingText: String)
    : CaseUiTestHandler() {


    override fun handleCaseTest() {
        ProcessDeathTestEnvironmentSetuper(applicationContext).setupTestEnvironment()

        clickOnView(R.id.new_button)
        checkViewAppearance(R.id.word_typing_text_view)

        onView(ViewMatchers.withId(R.id.word_typing_text_view))
            .perform(ViewActions.typeText(defaultTypingText))

        mainActivityScenario.recreate()

        onView(ViewMatchers.withId(R.id.word_typing_text_view))
            .check(ViewAssertions.matches(ViewMatchers.withText(defaultTypingText)))
    }


    override fun doAfterCaseTestHandling() {}
}