package com.example.englishrussianflashcards

import android.database.sqlite.SQLiteException
import androidx.lifecycle.SavedStateHandle
import com.example.englishrussianflashcards.domain.Card
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


/**
 * Created by Igor Aghibalov on 08.05.2024
 */
class ContinueScreenViewModelTester: ViewModelTester() {
    private lateinit var fakeCardList: List<Card>

    override fun setupTestEnvironment() {
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
        liveDataWrapper = CardListLiveDataWrapper<Result<List<Card>>>()
        repository = SuccessFakeCardRepository()
        viewModel = ContinueScreenViewModel(repository, liveDataWrapper)

        extractCardList(viewModel)
        assertCardListExtractionResult(viewModel, successResult)
    }

    @Test
    fun testCardListExtractionFailure() {
        val failureResult = Result.failure<SQLiteException>(SQLiteException())
        liveDataWrapper = CardListLiveDataWrapper<Result<SQLiteException>>()
        repository = FailureFakeCardRepository()
        viewModel = ContinueScreenViewModel(repository, liveDataWrapper)

        extractCardList(viewModel)
        assertCardListExtractionResult(viewModel, failureResult)
    }


    @Test
    fun testBundleDataRestore() {
        val bundleWrapper = FakeBundleWrapper()
        liveDataWrapper = CardListLiveDataWrapper<Result<List<Card>>>()
        repository = SuccessFakeCardRepository()
        viewModel = ContinueScreenViewModel(repository, liveDataWrapper)

        runBlocking {
            viewModel.fetchCardList()
            viewModel.saveData(bundleWrapper)

            viewModel = ContinueScreenViewModel(repository, liveDataWrapper)
            viewModel.restoreData(bundleWrapper)

            assertTrue(DATA_RESTORE_TEST_FAILURE_MESSAGE, viewModel.containsData(fakeCardList))
        }
    }


    fun extractCardList(viewModel: FlashcardsAppViewModel) {

        runTest {
            val cardListExtractionJob = launch { viewModel.extractCardList() }
            cardListExtractionJob.join()
        }
    }

    fun assertCardListExtractionResult(viewModel: FlashcardsAppViewModel,
                                       extractionResult: Result<*>) {
        assertEquals(true, viewModel.hasCardListExtractionResult(extractionResult))
    }
}