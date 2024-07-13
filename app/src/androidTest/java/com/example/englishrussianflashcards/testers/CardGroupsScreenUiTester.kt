package com.example.englishrussianflashcards.testers

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.R
import com.example.englishrussianflashcards.casetesthandlers.inflation.GroupCardsSuccessfulDisplayTestHandler
import com.example.englishrussianflashcards.casetesthandlers.rotation.ScrollPositionRetentionOnScreenRotationTestHandler
import com.example.englishrussianflashcards.casetesthandlers.inflation.WordGroupFragmentInflationTestHandler
import com.example.englishrussianflashcards.presentation.GroupCardViewHolder
import org.junit.Test

/**
 * Created by Igor Aghibalov on 25.04.2024
 */
class CardGroupsScreenUiTester: UiTester() {

    override fun setupTestEnvironment() {
        super.setupTestEnvironment()
        onView(withId(R.id.cards_button)).perform(click())
        onView(withId(R.id.group_card_recycler_view)).check(matches(isDisplayed()))
    }


    @Test
    fun testGroupCardsCorrectDisplay() {
        testCase(caseTestHandler = GroupCardsSuccessfulDisplayTestHandler(applicationContext))
    }


    @Test
    fun testWordGroupFragmentInflationOnGroupCardClick() {
        testCase(caseTestHandler = WordGroupFragmentInflationTestHandler())
    }

    @Test
    fun testGridScrollPositionRetention() {
        testCase(caseTestHandler = ScrollPositionRetentionOnScreenRotationTestHandler<GroupCardViewHolder>(R.id.group_card_recycler_view,
                                                                                      R.id.word_group_name_text_view,
                                                                                      applicationContext)
        )
    }
}