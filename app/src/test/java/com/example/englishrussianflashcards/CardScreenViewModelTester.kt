package com.example.englishrussianflashcards

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Igor Aghibalov on 08.05.2024
 */
class CardScreenViewModelTester: ViewModelTester() {
    private lateinit var fakeCardList: List<Card>


    @Before
    override fun setup() {
        fakeCardRepository = FakeCardRepository()
        fakeSavedStateHandle = SavedStateHandle(mapOf("word" to "apple"))
        viewModel = CardScreenViewModel(fakeCardRepository, fakeSavedStateHandle)
        val fakeWord = "apple"
        val fakeTranslation = "яблоко"
        fakeCardList = listOf(Card(fakeWord, fakeTranslation))
    }


    @Test
    fun testCardListExtraction() {

        runTest {
            viewModel.extractCardList()
        }

        assertEquals(true, viewModel.hasList(fakeCardList))
    }


    @Test
    fun testWordLiveDataValueAssignmentWithSavedStateHandle() {
        val testWord: String = fakeSavedStateHandle["word"]!!
        assertEquals(true, viewModel.isWordLiveDataValueEqual(testWord))
    }


    fun CardScreenViewModel.isWordLiveDataValueEqual(wordToCompare: String): Boolean {
        return wordLiveData.value == wordToCompare
    }
}