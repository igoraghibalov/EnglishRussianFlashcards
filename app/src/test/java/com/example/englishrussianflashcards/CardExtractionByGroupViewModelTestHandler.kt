package com.example.englishrussianflashcards

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue

/**
 * Created by Igor Aghibalov on 13.07.2024
 */
class CardExtractionByGroupViewModelTestHandler<T: Any>(private val flashcardsAppViewModel: FlashcardsAppViewModel,
                                                   private val extractionResultValue: T,
                                                   private val cardGroupName: String): CaseTestHandler {


    override fun handleCaseTest() {
        val result = Result.success(extractionResultValue)

        runTest {
            flashcardsAppViewModel.fetchCardList(cardGroupName)
            assertTrue(flashcardsAppViewModel.hasExtractionResult(result))
        }
    }
}