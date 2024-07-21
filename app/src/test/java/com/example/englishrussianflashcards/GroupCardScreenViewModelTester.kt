package com.example.englishrussianflashcards

import android.database.sqlite.SQLiteException
import com.example.englishrussianflashcards.domain.Card
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Igor Aghibalov on 10.05.2024
 */

class GroupCardScreenViewModelTester: ViewModelTester<Map<String, Int>>() {
    private lateinit var fakeGroupCardsCountMap: Map<String, Int>


    override fun setupTestEnvironment() {
        fakeGroupCardsCountMap = mapOf("Fruits" to 1)
        liveDataWrapper = GroupedCardsMapLiveDataWrapper<Result<Map<String, Int>>>()
        repository = SuccessGroupCardsCountMapRepository()
        viewModel = GroupCardScreenViewModel(repository, liveDataWrapper)
    }


    @Test
    fun testGroupCardsSuccessfulExtraction() {
        val successResult = Result.success(fakeGroupCardsCountMap)
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
            viewModel.extractGroupCardsCountMap()
        }
    }


    fun assertGroupedCardsMapEquality(viewModel: FlashcardsAppViewModel,
                                      extractionResult: Result<*>) {
        assertEquals(true, viewModel.hasExtractionResult(extractionResult))
    }

    override fun recreateViewModel() {
        GroupCardScreenViewModel(repository, liveDataWrapper)
    }
}