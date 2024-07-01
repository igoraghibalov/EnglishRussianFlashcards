package com.example.englishrussianflashcards

import com.example.englishrussianflashcards.casetesthandlers.inflation.GroupCardsSuccessfulDisplayTestHandler
import com.example.englishrussianflashcards.casetesthandlers.screenrotation.ScrollPositionRetentionOnScreenRotationTestHandler
import com.example.englishrussianflashcards.casetesthandlers.inflation.WordGroupFragmentInflationTestHandler
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
        testCase(caseTestHandler = ScrollPositionRetentionOnScreenRotationTestHandler(R.id.card_group_recycler_view,
                                                                                      R.id.word_group_name_text_view,
                                                                                      applicationContext))
    }
}