package com.example.englishrussianflashcards

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
        viewModel = CardScreenViewModel(fakeCardRepository)
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
}