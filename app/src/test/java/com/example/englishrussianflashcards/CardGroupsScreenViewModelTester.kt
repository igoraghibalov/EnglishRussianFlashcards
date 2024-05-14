package com.example.englishrussianflashcards

import android.database.sqlite.SQLiteException
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Igor Aghibalov on 10.05.2024
 */
class CardGroupsScreenViewModelTester: ViewModelTester() {
    private lateinit var fakeCardGroupMap: Map<String, List<Card>>


    @Before
    override fun setup() {
        val fakeWord = "apple"
        val fakeTranslation = "яблоко"
        fakeCardGroupMap = mapOf("Fruits" to Card(fakeWord, fakeTranslation))
    }


    @Test
    fun testCardsInGroupsSuccessfulExtraction() {
        val successResult = Result.success(fakeCardGroupMap)
        fakeCardRepository = SuccessFakeCardRepository()
        viewModel = CardGroupsScreenViewModel(fakeCardRepository, fakeSavedStateHandle, SavedStateHandle())

        runTest {
            viewModel.extractCardsInGroups()
        }
        assertEquals(true, viewModel.hasCardGroupMapExtractionResult(successResult))
    }

    @Test
    fun testCardsInGroupsFailureExtraction() {
        val failureResult = Result.failure<SQLiteException>(SQLiteException())
        fakeCardRepository = FailureFakeCardRepository()
        viewModel = CardGroupsScreenViewModel(fakeCardRepository, fakeSavedStateHandle, SavedStateHandle())

        runTest {
            viewModel.extractCardsInGroups()
        }
        assertEquals(true, viewModel.hasCardGroupMapExtractionResult(failureResult))
    }

    @Test
    fun testSavedStateHandleUpdateOnSuccessCardsInGroupsExtraction() {
        val successResult = Result.success(fakeCardGroupMap)
        fakeCardRepository = SuccessFakeCardRepository()
        viewModel = CardGroupsScreenViewModel(fakeCardRepository, fakeSavedStateHandle, SavedStateHandle())

        runTest {
            viewModel.extractCardsInGroups()
        }
        assertEquals(true, viewModel.doesSavedStateHandleContain(fakeCardGroupMap))
    }
}