package com.example.englishrussianflashcards

import android.database.sqlite.SQLiteException
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Created by Igor Aghibalov on 14.07.2024
 */
class CardDemoScreenViewModelTester: ViewModelTester() {
    private lateinit var demoCard: Card


    override fun setupTestEnvironment() {
        demoCard = Card("apple")
        liveDataWrapper = CardDemoLiveDataWrapper<UiState>()
    }

    @Test
    fun testDemoCardExtractionSuccess() {
        val successResult = UiState.Success(demoCard)
        repository = SuccessDemoCardRepository(demoCard)
        viewModel = CardDemoScreenViewModel(repository, liveDataWrapper)

        runTest {
            viewModel.fetchDemoCard()
            assertTrue("Demo card extraction failure",
                       viewModel.isExtractionResultPresent(successResult))
        }
    }


    @Test
    fun testDemoCardExtractionFailure() {
        val failureResult = UiState.Error()

        // TODO: repository must throw exception on demoCard extraction
        repository = FailureDemoCardRepository()
        viewModel = CardDemoScreenViewModel(repository, liveDataWrapper)

        runTest {
            viewModel.fetchDemoCard()
            assertTrue("Demo card extraction exception handling failure",
                       viewModel.isExtractionResultPresent(failureResult))
        }
    }
}