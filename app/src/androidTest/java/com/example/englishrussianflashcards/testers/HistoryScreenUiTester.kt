package com.example.englishrussianflashcards.testers

import com.example.englishrussianflashcards.R
import com.example.englishrussianflashcards.casetesthandlers.inflation.CardFragmentInflationOnRecyclerViewItemClickTestHandler
import com.example.englishrussianflashcards.casetesthandlers.rotation.ScrollPositionRetentionOnScreenRotationTestHandler
import com.example.englishrussianflashcards.presentation.CardHistoryViewHolder
import org.junit.Test

/**
 * Created by Igor Aghibalov on 01.05.2024
 */


class HistoryScreenUiTester: UiTester() {

    @Test
    fun testCardFragmentInflationOnHistoryRecyclerViewItemClick() {
        testCase(caseTestHandler = CardFragmentInflationOnRecyclerViewItemClickTestHandler(
            R.id.card_history_recycler_view
        ))
    }

    @Test
    fun testScrollPositionRetentionOnScreenRotation() {
       testCase(caseTestHandler
            = ScrollPositionRetentionOnScreenRotationTestHandler<CardHistoryViewHolder>(R.id.card_history_recycler_view,
                                                                                        R.id.history_word_text_view,
                                                                                        applicationContext ))
    }
}