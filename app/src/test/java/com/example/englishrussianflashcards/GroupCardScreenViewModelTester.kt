package com.example.englishrussianflashcards

import android.database.sqlite.SQLiteException
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Igor Aghibalov on 10.05.2024
 */

class GroupCardScreenViewModelTester: ViewModelTester() {

    //TODO: replace 'lateinit var' with a default class object initialization
    private lateinit var fakeGroupedCardsMap: Map<String, List<Card>>


    override fun setupTestEnvironment() {
        val fakeWord = "apple"
        val fakeTranslation = "яблоко"
        fakeGroupedCardsMap = mapOf("Fruits" to listOf(Card(fakeWord, fakeTranslation)))
    }


    @Test
    fun testGroupCardsSuccessfulExtraction() {
        val successResult = Result.success(fakeGroupedCardsMap)
        liveDataWrapper = GroupedCardsMapLiveDataWrapper<Result<Map<String, List<Card>>>>()
        repository = SuccessGroupedCardsMapRepository()
        viewModel = GroupCardScreenViewModel(repository, liveDataWrapper)

        runTest {
            viewModel.extractCardsInGroups()
        }
        assertEquals(true, viewModel.hasGroupedCardsMapExtractionResult(successResult))
    }

    @Test
    fun testGroupCardsFailureExtraction() {
        val failureResult = Result.failure<SQLiteException>(SQLiteException())
        repository = FailureGroupedCardsMapRepository()
        liveDataWrapper = GroupedCardsMapLiveDataWrapper<Result<SQLiteException>>()
        viewModel = GroupCardsScreenViewModel(repository, liveDataWrapper)

        runTest {
            viewModel.extractCardsInGroups()
        }
        assertEquals(true, viewModel.hasCardGroupMapExtractionResult(failureResult))
    }
}