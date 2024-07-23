package com.example.englishrussianflashcards

import android.graphics.Picture
import android.graphics.drawable.Drawable
import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import com.almworks.sqlite4java.SQLiteException
import com.example.englishrussianflashcards.domain.LiveDataWrapper
import com.example.englishrussianflashcards.domain.Repository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.Serializable

/**
 * Created by Igor Aghibalov on 15.05.2024
 */

/*
TODO:
 1) Implement test cases:
       a) (Translation, Example, Image) selection attempt on no word typing
       b) Data extraction success
       c) Image extraction failure
               a) No internet connection
               b) IoException(data transfer failure)
       d) Transcription not found in a DictionaryRepository
  2) Remove redundant properties to use base class ones
 */
class CardCreationScreenViewModelTester: ViewModelTester() {
    private lateinit var fakeGroupTitleList: List<String>
    private lateinit var fakeWordList: List<String>
    private lateinit var fakeWord: String
    private lateinit var fakeImageList: List<Drawable>
    private lateinit var fakeTranscription: String
    private lateinit var dataExtractionSuccessResultMap: Map<String, Result<Any>>
    private lateinit var dataExtractionFailureResultMap: Map<String, Result<Any>>
    private val roomFailureResult = Result.failure<SQLiteException>(android.database.sqlite.SQLiteException())
    private val coilFailureResult = Result.failure<FakeImageLoadingException>(FakeImageLoadingException())
    private lateinit var failureDataExtractionViewModel: CardCreationScreenViewModel
    private lateinit var successDataExtractionViewModel: CardCreationScreenViewModel

    override fun recreateViewModel() {
        TODO("Not yet implemented")
    }

    override fun setupTestEnvironment() {
        fakeGroupTitleList = listOf("Fruits", "Animals")
        fakeWordList = listOf("Apple", "Arrow")
        fakeWord = "Apple"
        fakeImageList = listOf(PictureDrawable(Picture()))
        fakeTranscription = "[ˈæpəl]"
        dataExtractionSuccessResultMap = mapOf("groupTitleList" to Result.success(fakeGroupTitleList),
                                                "wordList" to Result.success(fakeWordList),
                                                "word" to Result.success(fakeWord),
                                                "imageList" to Result.success(fakeImageList),
                                                "transcription" to Result.success(fakeTranscription))

        dataExtractionFailureResultMap = mapOf("groupTitleList" to roomFailureResult,
                                                "wordList" to roomFailureResult,
                                                "word" to roomFailureResult,
                                                "imageList" to coilFailureResult,
                                                "transcription" to roomFailureResult)



        var fakeRepository = FailureFakeCardRepository()
        val failureFakeImageRepository = FailureFakeImageRepository()
        val failureFakeDictionaryRepository = FailureFakeDictionaryRepository()

        val successFakeImageRepository = SuccessFakeImageRepository()
        val successFakeDictionaryRepository = SuccessFakeDictionaryRepository()

        failureDataExtractionViewModel = CardCreationScreenViewModel(fakeRepository,
                                                                     failureFakeImageRepository,
                                                                     failureFakeDictionaryRepository)
        fakeRepository = SuccessFakeCardRepository()

        successDataExtractionViewModel = CardCreationScreenViewModel(fakeRepository,
                                                                     successFakeImageRepository,
                                                                     successFakeDictionaryRepository,
                                                                     SavedStateHandle())
    }


    @Test
    fun testSuccessfulDataExtraction() {

        runTest {
            joinViewModelDataExtractionJob(this, successDataExtractionViewModel)
            assertEquals(true, successDataExtractionViewModel.hasDataExtractionResult(dataExtractionSuccessResultMap))
        }
    }

    @Test
    fun testFailureDataExtraction() {

        runTest {
            joinViewModelDataExtractionJob(this, failureDataExtractionViewModel)
            assertEquals(true, failureDataExtractionViewModel.hasDataExtractionResult(dataExtractionFailureResultMap))
        }
    }


    @Test
    fun testTranslationSelectionOnNoWordTyping() {
        repository = FakeDictionaryRepository()
        viewModel = CardCreationScreenViewModel(repository)

        runBlocking {
            val expectedUiState: UiState = UiState.Error(EMPTY_WORD_EXCEPTION_MESSAGE)
            val actualUiState: UiState = viewModel.getTranslationList(word = "")
            assertEquals(expectedUiState, actualUiState)
        }
    }


    fun applyMultipleDataExtractionCall(cardCreationScreenViewModel: CardCreationScreenViewModel) {

        cardCreationScreenViewModel.apply {
            getWordList()
            getImageList()
            getTranslationList()
            getTranscription()
            getExampleList()
            getGroupTitleList()
        }
    }


    suspend fun joinViewModelDataExtractionJob(testScope: TestScope,
                                               cardCreationScreenViewModel: CardCreationScreenViewModel) {

        val dataExtractionJob = testScope.launch {
            applyMultipleDataExtractionCall(cardCreationScreenViewModel)
        }

        dataExtractionJob.join()
    }
}