package com.example.englishrussianflashcards

import android.graphics.Picture
import android.graphics.drawable.Drawable
import android.graphics.drawable.PictureDrawable
import androidx.lifecycle.SavedStateHandle
import com.almworks.sqlite4java.SQLiteException
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Igor Aghibalov on 15.05.2024
 */
// TODO: combine multiple success/failure tests into single test methods respectively
class CardCreationScreenViewModelTester: ViewModelTester()  {
    private lateinit var fakeGroupTitleList: List<String>
    private lateinit var fakeWordList: List<String>
    private lateinit var fakeWord: String
    private lateinit var fakeImageList: List<Drawable>
    private val roomFailureResult = Result.failure<SQLiteException>(android.database.sqlite.SQLiteException())
    private lateinit var failureDataExtractionViewModel: CardCreationScreenViewModel
    private lateinit var successDataExtractionViewModel: CardCreationScreenViewModel

    override fun setup() {
        fakeGroupTitleList = listOf("Fruits", "Animals")
        fakeWordList = listOf("Apple", "Arrow")
        fakeWord = "Apple"
        fakeImageList = listOf(PictureDrawable(Picture()))


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
    fun testSuccessGroupTitleListExtraction() {
        val successfulResult = Result.success(fakeGroupTitleList)

        runTest {
            successDataExtractionViewModel.getGroupTitleList()
        }

        assertEquals(true, failureDataExtractionViewModel.hasDataExtractionResult(successfulResult))
    }


    @Test
    fun testFailureGroupTitleListExtraction() {

        runTest {
            failureDataExtractionViewModel.getGroupTitleList()
        }

        assertEquals(true, failureDataExtractionViewModel.hasDataExtractionResult(roomFailureResult))
    }


    @Test
    fun testSuccessWordListExtraction() {
        val successfulResult = Result.success(fakeWordList)

        runTest {
            successDataExtractionViewModel.getWordList()
        }

        assertEquals(true, failureDataExtractionViewModel.hasDataExtractionResult(successfulResult))
    }


    @Test
    fun testFailureWordListExtraction() {

        runTest {
            failureDataExtractionViewModel.getWordList()
        }

        assertEquals(true, failureDataExtractionViewModel.hasDataExtractionResult(roomFailureResult))
    }


    @Test
    fun testSuccessImageListExtraction() {
        val successfulResult = Result.success(fakeImageList)

        runTest {
            successDataExtractionViewModel.getImageList(fakeWord)
        }

        assertEquals(true, failureDataExtractionViewModel.hasDataExtractionResult(successfulResult))
    }


    @Test
    fun testFailureImageListExtraction() {

        runTest {
            failureDataExtractionViewModel.getImageList(fakeWord)
        }

        assertEquals(true, failureDataExtractionViewModel.hasDataExtractionResult(roomFailureResult))
    }

}
