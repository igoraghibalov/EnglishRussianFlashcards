package com.example.englishrussianflashcards

import android.database.sqlite.SQLiteException
import androidx.core.os.bundleOf
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
        fakeCardRepository = SuccessFakeCardRepository()
        fakeSavedStateHandle = SavedStateHandle(mapOf("cardList" to successResult))
        viewModel = CardScreenViewModel(fakeCardRepository, fakeSavedStateHandle)

        runTest {
            viewModel.extractCardList()
        }
        assertEquals(true, viewModel.hasCardListExtractionResult(successResult))
    }

    @Test
    fun testCardListExtractionFailure() {
        val failureResult = Result.failure<SQLiteException>(SQLiteException())
        fakeCardRepository = FailureFakeCardRepository()
        fakeSavedStateHandle = SavedStateHandle(mapOf("cardList" to failureResult))
        viewModel = CardScreenViewModel(fakeCardRepository, fakeSavedStateHandle)
        runTest {
            viewModel.extractCardList()
        }
        assertEquals(true, viewModel.hasCardListExtractionResult(failureResult))
    }


    @Test
    fun testCardListLiveDataValueAssignmentWithSavedStateHandle() {
        fakeCardRepository = FailureFakeCardRepository()
        fakeSavedStateHandle = SavedStateHandle(mapOf("cardList" to Result.success(fakeCardList)))
        viewModel = CardScreenViewModel(fakeCardRepository, fakeSavedStateHandle)
        val cardListExtractionResultToCompare: Result<List<Card>> = fakeSavedStateHandle["cardList"]!!
        assertEquals(true, viewModel.isCardListExtractionResultLiveDataValueEqual(cardListExtractionResultToCompare))
    }


    fun <T: Any> CardScreenViewModel.isCardListExtractionResultLiveDataValueEqual(cardListExtractionResultToCompare: Result<T>): Boolean {
        return cardListLiveData.value == cardListToCompare
    }
}