package com.example.englishrussianflashcards

import com.example.englishrussianflashcards.casetesthandlers.GroupCardsSuccessfulDisplayTestHandler
import com.example.englishrussianflashcards.casetesthandlers.ScrollPositionRetentionOnScreenRotationTestHandler
import com.example.englishrussianflashcards.casetesthandlers.WordGroupFragmentInflationTestHandler
import org.junit.Test

/**
 * Created by Igor Aghibalov on 25.04.2024
 */
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
    fun testGridScrollPositionRetention() {
        testCase(caseTestHandler = ScrollPositionRetentionOnScreenRotationTestHandler(applicationContext))
    }
}