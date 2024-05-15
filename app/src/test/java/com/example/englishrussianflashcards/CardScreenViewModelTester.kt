package com.example.englishrussianflashcards

import android.database.sqlite.SQLiteException
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


/**
 * Created by Igor Aghibalov on 08.05.2024
 */
class CardScreenViewModelTester: ViewModelTester() {
    private lateinit var fakeCardList: List<Card>


    @Before
    override fun setup() {
        val fakeWord = "apple"
        val fakeTranslation = "яблоко"
        val wasDisplayed = true
        fakeCardList = listOf(Card(fakeWord, fakeTranslation, wasDisplayed))
    }


    @Test
    fun testCardListExtractionSuccess() {
        val successResult = Result.success(fakeCardList)
        val fakeRepository = SuccessFakeCardRepository()
        fakeSavedStateHandle = SavedStateHandle(mapOf("cardList" to successResult))
        val viewModel = CardScreenViewModel(fakeRepository, fakeSavedStateHandle)

        runTest {
            viewModel.extractCardList()
        }
        assertEquals(true, viewModel.hasCardListExtractionResult(successResult))
    }

    @Test
    fun testCardListExtractionFailure() {
        val failureResult = Result.failure<SQLiteException>(SQLiteException())
        val fakeRepository = FailureFakeCardRepository()
        fakeSavedStateHandle = SavedStateHandle(mapOf("cardList" to failureResult))
        val viewModel = CardScreenViewModel(fakeRepository, fakeSavedStateHandle)
        runTest {
            viewModel.extractCardList()
        }
        assertEquals(true, viewModel.hasCardListExtractionResult(failureResult))
    }


    @Test
    fun testCardListLiveDataValueAssignmentWithSavedStateHandle() {
        val fakeRepository = FailureFakeCardRepository()
        fakeSavedStateHandle = SavedStateHandle(mapOf("cardList" to Result.success(fakeCardList)))
        val viewModel = CardScreenViewModel(fakeRepository, fakeSavedStateHandle)
        val cardListExtractionResultToCompare: Result<List<Card>> = fakeSavedStateHandle["cardList"]!!
        assertEquals(true, viewModel.isCardListExtractionResultLiveDataValueEqual(cardListExtractionResultToCompare))
    }


    fun <T: Any> CardScreenViewModel.isCardListExtractionResultLiveDataValueEqual(cardListExtractionResultToCompare: Result<T>): Boolean {
        return cardListLiveData.value == cardListToCompare
    }
}