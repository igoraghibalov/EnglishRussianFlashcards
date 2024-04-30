package com.example.englishrussianflashcards

import android.app.Application
import android.os.Build
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Test

/**
 * Created by Igor Aghibalov on 25.04.2024
 */
class CardsScreenUiTester: UiTester() {

    override fun setup() {
        super.setup()
        setupProcessDeathTestEnvironment()
    }


    @Test
    fun testWordGroupFragmentInflationOnGroupCardClick() {
        testFragmentInflation(R.id.cards_button, R.id.card_group_recycler_view)
        val cardGroupRecyclerViewMatcher = withId(R.id.card_group_recycler_view)
        val cardGroupRecyclerViewInteraction = onView(cardGroupRecyclerViewMatcher)
        val cardGroupRecyclerViewItemClickAction = RecyclerViewActions.actionOnItemAtPosition<GroupCardViewHolder>(0, click())
        cardGroupRecyclerViewInteraction.perform(cardGroupRecyclerViewItemClickAction)
        checkViewAppearance(R.id.add_word_button)
    }


    @Test
    fun testCardsFragmentInflationAfterProcessDeath() {
        testFragmentInflation(R.id.cards_button, R.id.card_group_recycler_view)
        mainActivityScenario.recreate()
        checkViewAppearance(R.id.card_group_recycler_view)
    }


    @Test
    fun testCardsFragmentInflationAfterDeviceRotation() {
        testFragmentInflation(R.id.cards_button, R.id.card_group_recycler_view)
        rotateScreen()
        checkViewAppearance(R.id.card_group_recycler_view)
    }
}