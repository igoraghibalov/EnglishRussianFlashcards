package com.example.englishrussianflashcards


import android.database.sqlite.SQLiteException
import androidx.lifecycle.SavedStateHandle
import com.example.englishrussianflashcards.domain.Card
import com.example.englishrussianflashcards.domain.Repository
import com.example.englishrussianflashcards.presentation.ObservableContainer
import com.example.englishrussianflashcards.presentation.ViewPagerItemScreenViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test


/**
 * Created by Igor Aghibalov on 10.06.2024
 */
class ViewPagerItemScreenViewModelTester: ViewModelTester() {
    private lateinit var cardObservableContainer: ObservableContainer<Card>
    private lateinit var failureCardExtractionRepository: Repository
    private lateinit var successCardExtractionRepository: Repository
    private lateinit var viewPagerItemCard: Card


    override fun setup() {
        cardObservableContainer = ViewPagerItemCardObservableContainer()
        failureCardExtractionRepository = FailureCardExtractionRepository()
        successCardExtractionRepository = SuccessCardExtractionRepository()
        fakeSavedStateHandle = SavedStateHandle()
        viewPagerItemCard = Card(id = 0,
                                 word = "apple",
                                 translation = "яблоко",
                                 transcription = "[]",
                                 group = "Fruits",
                                 isDisplayed = false)
    }



    @Test
    fun testCardExtractionSuccess() {

        runTest {
            val viewModel = ViewPagerItemScreenViewModel(cardObservableContainer,
                                                         successCardExtractionRepository,
                                                         fakeSavedStateHandle)
            val successResult = Result.success(viewPagerItemCard)

           testCardExtractionResult(viewModel, successResult, this)
        }
    }


    @Test
    fun testCardExtractionFailure() {

        runTest {
            val viewModel = ViewPagerItemScreenViewModel(cardObservableContainer,
                                                         failureCardExtractionRepository,
                                                         fakeSavedStateHandle)
            val failureResult = Result.failure<SQLiteException>(SQLiteException())
            testCardExtractionResult(viewModel, failureResult, this)
        }
    }


    suspend fun <T> testCardExtractionResult(viewModel: ViewPagerItemScreenViewModel,
                                             cardExtractionResult: Result<T>,
                                             testScope: TestScope) {
        val viewPagerCardExtractionJob: Job
        val cardInsertionJob = testScope.launch {
            viewModel.insertCard(viewPagerItemCard)
        }

        cardInsertionJob.join()

        viewPagerCardExtractionJob =  testScope.launch {
            viewModel.extractViewPagerItemCard()
        }

        viewPagerCardExtractionJob.join()

        assertEquals(true, viewModel.hasExpectedExtractionResult(cardExtractionResult))
    }
}