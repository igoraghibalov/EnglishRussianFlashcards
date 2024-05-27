package com.example.englishrussianflashcards

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.sql.SQLException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Created by Igor Aghibalov on 19.05.2024
 */
class HistoryScreenViewModelTester: ViewModelTester() {
    private lateinit var successDataExtractionViewModel: HistoryScreenViewModel
    private lateinit var failureDataExtractionViewModel: HistoryScreenViewModel
    private lateinit var fakeDate: String
    private lateinit var failureResultValue: SQLException
    private lateinit var successResultValue: Map<String, String>


    override fun setup() {
        fakeDate = LocalDate.now().format(DateTimeFormatter.ofPattern("d.MM.yyyy"))
        successResultValue = mapOf("apple" to fakeDate)
        failureResultValue = SQLException()
        val successWordMapExtractionFakeRepository = SuccessWordMapExtractionFakeRepository()
        val failureWordMapExtractionFakeRepository = FailureWordMapExtractionFakeRepository()
        fakeSavedStateHandle = SavedStateHandle()
        successDataExtractionViewModel = HistoryScreenViewModel(successWordMapExtractionFakeRepository, fakeSavedStateHandle)
        failureDataExtractionViewModel = HistoryScreenViewModel(failureWordMapExtractionFakeRepository, fakeSavedStateHandle)
    }


    @Test
    fun testSuccessWordMapExtraction() {
        val successWordMapExtractionResult = Result.success(successResultValue)

        runTest {
            assertWordMapExtractionResult(successDataExtractionViewModel, successWordMapExtractionResult, this)
        }
    }


    @Test
    fun testFailureWordMapExtraction() {
        val failureWordMapExtractionResult = Result.failure<Throwable>(failureResultValue)

        runTest {
            assertWordMapExtractionResult(failureDataExtractionViewModel, failureWordMapExtractionResult, this)
        }
    }


    suspend fun<T: Any> assertWordMapExtractionResult(viewModel: HistoryScreenViewModel,
                                                      extractionResult: Result<T>,
                                                      testScope: TestScope) {

        val wordMapExtractionJob = testScope.launch { viewModel.getWordMap() }
        wordMapExtractionJob.join()
        assertEquals(true, viewModel.hasDataExtractionResult(extractionResult))
    }
}