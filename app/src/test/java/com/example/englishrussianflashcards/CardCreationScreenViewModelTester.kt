package com.example.englishrussianflashcards

import android.graphics.Picture
import android.graphics.drawable.Drawable
import android.graphics.drawable.PictureDrawable
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Igor Aghibalov on 15.05.2024
 */

/*
TODO:
 1) Implement test cases:
       - (Translation, Example, Image) selection attempt on no word typing
       - Data extraction success
       - Image extraction failure
               a) No internet connection
               b) IoException(data transfer failure)
       - Transcription not found in a DictionaryRepository
       - Translation not found in a DictionaryRepository
       - Example not found in a DictionaryRepository
 */
class CardCreationScreenViewModelTester: ViewModelTester() {
    private lateinit var fakeGroupTitleList: List<String>
    private lateinit var fakeWordList: List<String>
    private lateinit var fakeWord: String
    private lateinit var fakeImageList: List<Drawable>
    private lateinit var fakeTranscription: String
    private lateinit var dataExtractionSuccessResultMap: Map<String, UiState>
    private lateinit var dataExtractionFailureResultMap: Map<String, UiState>
    private val roomFailureResult = UiState.Error(android.database.sqlite.SQLiteException())
    private val networkFailureResult = UiState.Error(Throwable())


    override fun setupTestEnvironment() {
        fakeGroupTitleList = UiState.Success(listOf("Fruits", "Animals"))
        fakeWordList = UiState.Success(listOf("Apple", "Arrow"))
        fakeWord = UiState.Success("Apple")
        fakeImageList = UiState.Success(listOf(PictureDrawable(Picture())))
        fakeTranscription = UiState.Success("[ˈæpəl]")
        dataExtractionSuccessResultMap = mapOf("groupTitleList" to fakeGroupTitleList,
                                                "wordList" to fakeWordList,
                                                "word" to fakeWord,
                                                "imageList" to fakeImageList,
                                                "transcription" to fakeTranscription)

        dataExtractionFailureResultMap = mapOf("groupTitleList" to roomFailureResult,
                                                "wordList" to roomFailureResult,
                                                "word" to roomFailureResult,
                                                "imageList" to networkFailureResult,
                                                "transcription" to roomFailureResult)
    }


    @Test
    fun testSuccessfulDataExtraction() {
        val dictionaryRepository = FakeSuccessDictionaryRepository()
        val imageRepository = FakeSuccessImageRepository()
        viewModel = CardCreationScreenViewModel(dictionaryRepository, imageRepository)

        runTest {
            joinViewModelDataExtractionJob(this, viewModel)
            assertEquals(true, viewModel.hasDataExtractionResult(dataExtractionSuccessResultMap))
        }
    }

    @Test
    fun testFailureDataExtraction() {
        val dictionaryRepository = FakeFailureDictionaryRepository()
        val imageRepository = FakeFailureImageRepository()
        viewModel = CardCreationScreenViewModel(dictionaryRepository, imageRepository)

        runTest {
            joinViewModelDataExtractionJob(this, viewModel)
            assertEquals(true, viewModel.hasDataExtractionResult(dataExtractionFailureResultMap))
        }
    }


    @Test
    fun testElementSelectionOnWordNoTyping() {

        repository = FakeDictionaryRepository()
        viewModel = CardCreationScreenViewModel(repository)

        runBlocking {
            val emptyWord = ""
            expectedData = UiState.Error(EMPTY_WORD_EXCEPTION_MESSAGE)
            var actualUiState: UiState = viewModel.getTranslationList(emptyWord)
            assertEquals(expectedData, actualUiState)

            actualUiState = viewModel.getExampleList(emptyWord)
            assertEquals(expectedData, actualUiState)

            actualUiState = viewModel.getImageList(emptyWord)
            assertEquals(expectedData, actualUiState)
        }
    }


    fun applyMultipleDataExtractionCall(cardCreationScreenViewModel: CardCreationScreenViewModel) {
        val word = "apple"

        cardCreationScreenViewModel.apply {
            getWordList(word)
            getImageList(word)
            getTranslationList(word)
            getTranscription(word)
            getExampleList(word)
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
