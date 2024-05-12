package com.example.englishrussianflashcards

import androidx.core.os.bundleOf
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
        val fakeWord = "apple"
        val fakeTranslation = "яблоко"
        val wasDisplayed = true
        fakeCardList = listOf(Card(fakeWord, fakeTranslation, wasDisplayed))
        fakeCardRepository = FakeCardRepository()
        fakeSavedStateHandle = SavedStateHandle(mapOf("cardList" to fakeCardList))
        viewModel = CardScreenViewModel(fakeCardRepository, fakeSavedStateHandle)
    }


    @Test
    fun testCardListExtraction() {

        runTest {
            viewModel.extractCardList()
        }

        assertEquals(true, viewModel.hasList(fakeCardList))
    }


    @Test
    fun testCardListLiveDataValueAssignmentWithSavedStateHandle() {
        val cardListToCompare: String = fakeSavedStateHandle["cardList"]!!
        assertEquals(true, viewModel.isCardListLiveDataValueEqual(cardListToCompare))
    }


    fun CardScreenViewModel.isCardListLiveDataValueEqual(cardListToCompare: String): Boolean {
        return cardListLiveData.value == cardListToCompare
    }
}