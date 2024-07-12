package com.example.englishrussianflashcards

import android.database.sqlite.SQLiteException
import androidx.lifecycle.SavedStateHandle
import com.example.englishrussianflashcards.presentation.FlashcardsApplicationViewModel
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

        extractCardsInGroups(viewModel)
        assertGroupedCardsMapEquality(viewModel, successResult)
    }

    @Test
    fun testGroupCardsFailureExtraction() {
        val failureResult = Result.failure<SQLiteException>(SQLiteException())
        repository = FailureGroupedCardsMapRepository()
        liveDataWrapper = GroupedCardsMapLiveDataWrapper<Result<SQLiteException>>()
        viewModel = GroupCardsScreenViewModel(repository, liveDataWrapper)

        extractCardsInGroups(viewModel)
        assertGroupedCardsMapEquality(viewModel, failureResult)
    }


    fun extractCardsInGroups(viewModel: FlashcardsAppViewModel) {

        runTest {
            viewModel.extractCardsInGroups()
        }
    }


    fun assertGroupedCardsMapEquality(viewModel: FlashcardsAppViewModel,
                                      extractionResult: Result<*>) {
        assertEquals(true, viewModel.hasCardGroupMapExtractionResult(extractionResult))
    }
}