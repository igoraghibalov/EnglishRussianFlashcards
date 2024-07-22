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
    private lateinit var failureDataExtractionViewModel: HistoryScreenViewModel
    private lateinit var fakeDate: String
    private lateinit var failureResultValue: UiState.Error
    private lateinit var successResultValue: UiState.Success

    override fun recreateViewModel() {
        viewModel = HistoryScreenViewModel(liveDataWrapper, repository)
    }


    override fun setupTestEnvironment() {
        fakeDate = LocalDate.now().format(DateTimeFormatter.ofPattern("d.MM.yyyy"))
        successResultValue = UiState.Success(listOf("apple" to fakeDate))
        failureResultValue = UiState.Error(SQLException())
    }


    @Test
    fun testSuccessWordMapExtraction() {
        liveDataWrapper = CardHistoryEntryListLiveDataWrapper()
        repository = FakeCardRepository()
        bundleWrapper = FakeBundleWrapper()
        viewModel = HistoryScreenViewModel(liveDataWrapper, repository)

        runTest {
            assertWordMapExtractionResult(viewModel, successResultValue, this)
        }
    }


    @Test
    fun testFailureWordMapExtraction() {

        runTest {
            assertWordMapExtractionResult(failureDataExtractionViewModel, failureResultValue, this)
        }
    }


    suspend fun assertWordMapExtractionResult(viewModel: HistoryScreenViewModel,
                                              uiState: UiState,
                                              testScope: TestScope) {

        val wordMapExtractionJob = testScope.launch { viewModel.getCardHistoryEntryList() }
        wordMapExtractionJob.join()
        assertEquals(true, viewModel.hasDataExtractionResult(uiState))
    }
}