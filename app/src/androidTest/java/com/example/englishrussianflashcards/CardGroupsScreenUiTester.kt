package com.example.englishrussianflashcards

import com.example.englishrussianflashcards.casetesthandlers.GroupCardsSuccessfulDisplayTestHandler
import com.example.englishrussianflashcards.casetesthandlers.WordGroupFragmentInflationTestHandler
import org.junit.Test

/**
 * Created by Igor Aghibalov on 25.04.2024
 */
//TODO: 1) create testGridScrollPositionRetention() method;
//      2) refactor process death and screen rotation tests
class CardGroupsScreenUiTester: UiTester() {


    @Test
    fun testGroupCardsCorrectDisplay() {
        testCase(caseTestHandler = GroupCardsSuccessfulDisplayTestHandler(applicationContext))
    }


    @Test
    fun testWordGroupFragmentInflationOnGroupCardClick() {
        testCase(caseTestHandler = WordGroupFragmentInflationTestHandler())
    }


    @Test
    fun testCardsFragmentInflationAfterProcessDeath() {
        setupProcessDeathTestEnvironment()
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