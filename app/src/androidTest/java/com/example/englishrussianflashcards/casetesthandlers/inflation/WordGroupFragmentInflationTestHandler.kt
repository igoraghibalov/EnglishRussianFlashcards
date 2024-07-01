package com.example.englishrussianflashcards.casetesthandlers.inflation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.CaseUiTestHandler
import com.example.englishrussianflashcards.R
import com.example.englishrussianflashcards.presentation.GroupCardViewHolder

/**
 * Created by Igor Aghibalov on 28.06.2024
 */
class WordGroupFragmentInflationTestHandler: CaseUiTestHandler() {


    override fun handleCaseTest() {
        clickOnView(R.id.cards_button)
        checkViewAppearance(R.id.card_group_recycler_view)

        val cardGroupRecyclerViewMatcher = withId(R.id.card_group_recycler_view)
        val cardGroupRecyclerViewInteraction = onView(cardGroupRecyclerViewMatcher)
        val cardGroupRecyclerViewItemClickAction
            = RecyclerViewActions.actionOnItemAtPosition<GroupCardViewHolder>(0, click())

        cardGroupRecyclerViewInteraction.perform(cardGroupRecyclerViewItemClickAction)
        checkViewAppearance(R.id.add_word_button)
    }

    override fun doAfterCaseTestHandling() {}
}