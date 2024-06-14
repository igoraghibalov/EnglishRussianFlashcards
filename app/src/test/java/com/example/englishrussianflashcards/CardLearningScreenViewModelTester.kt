package com.example.englishrussianflashcards

import android.database.sqlite.SQLiteException
import androidx.lifecycle.SavedStateHandle
import com.example.englishrussianflashcards.domain.Card
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


/**
 * Created by Igor Aghibalov on 08.05.2024
 */
class CardLearningScreenViewModelTester: ViewModelTester() {
    private lateinit var fakeCardList: List<Card>


    @Before
    override fun setup() {
        val fakeCard = Card(id = 0,
                            word = "apple",
                            transcription = "[]",
                            translation = "яблоко",
                            isDisplayed =  true,
                            group = "Fruits")

        fakeCardList = listOf(fakeCard)
    }


    @Test
    fun testCardListExtractionSuccess() {
        val successResult = Result.success(fakeCardList)
        val fakeRepository = SuccessFakeCardRepository()
        fakeSavedStateHandle = SavedStateHandle(mapOf("cardList" to successResult))
        val viewModel = CardScreenViewModel(fakeRepository, fakeSavedStateHandle)

        runTest {
            val cardListExtractionJob = launch { viewModel.extractCardList() }
            cardListExtractionJob.join()
            assertEquals(true, viewModel.hasCardListExtractionResult(successResult))
        }
    }

    @Test
    fun testCardListExtractionFailure() {
        val failureResult = Result.failure<SQLiteException>(SQLiteException())
        val fakeRepository = FailureFakeCardRepository()
        fakeSavedStateHandle = SavedStateHandle(mapOf("cardList" to failureResult))
        val viewModel = CardScreenViewModel(fakeRepository, fakeSavedStateHandle)

        runTest {
            val cardListExtractionJob = launch { viewModel.extractCardList() }
            cardListExtractionJob.join()
            assertEquals(true, viewModel.hasCardListExtractionResult(failureResult))
        }
    }
}