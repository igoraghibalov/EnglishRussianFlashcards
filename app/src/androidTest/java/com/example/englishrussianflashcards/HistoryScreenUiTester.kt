package com.example.englishrussianflashcards

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.casetesthandlers.inflation.CardFragmentInflationOnRecyclerViewItemClickTestHandler
import com.example.englishrussianflashcards.casetesthandlers.screenrotation.ScrollPositionRetentionOnScreenRotationTestHandler
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.Test

/**
 * Created by Igor Aghibalov on 01.05.2024
 */


class HistoryScreenUiTester: UiTester() {

    @Test
    fun testCardFragmentInflationOnHistoryRecyclerViewItemClick() {
        testCase(caseTestHandler = CardFragmentInflationOnRecyclerViewItemClickTestHandler(R.id.card_history_recycler_view))
    }

    @Test
    fun testScrollPositionRetentionOnScreenRotation() {
       testCase(caseTestHandler = ScrollPositionRetentionOnScreenRotationTestHandler(R.id.card_history_recycler_view,
                                                                                     R.id.history_word_text_view,
                                                                                     applicationContext ))
    }
}