package com.example.englishrussianflashcards.casetesthandlers

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.englishrussianflashcards.CaseUiTestHandler
import com.example.englishrussianflashcards.R

/**
 * Created by Igor Aghibalov on 24.06.2024
 */
class CardDemoDialogFragmentInflationTestHandler: CaseUiTestHandler() {


    override fun handleCaseTest() {
        clickOnView(R.id.card_creation_button)
        checkViewAppearance(R.id.card_front_side_view)
    }


    override fun doAfterCaseTestHandling() {}
}