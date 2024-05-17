package com.example.englishrussianflashcards

import android.graphics.Picture
import android.graphics.drawable.Drawable
import android.graphics.drawable.PictureDrawable
import androidx.lifecycle.SavedStateHandle
import com.almworks.sqlite4java.SQLiteException
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Igor Aghibalov on 15.05.2024
 */
class CardCreationScreenViewModelTester: ViewModelTester()  {
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

    override fun setup() {
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
                                                                     failureFakeDictionaryRepository,
                                                                     SavedStateHandle())
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


    fun applyMultipleDataRequestCall(cardCreationScreenViewModel: CardCreationScreenViewModel) {

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
            applyMultipleDataRequestCall(cardCreationScreenViewModel)
        }

        dataExtractionJob.join()
    }
}
