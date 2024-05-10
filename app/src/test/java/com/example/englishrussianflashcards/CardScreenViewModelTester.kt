package com.example.englishrussianflashcards

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Igor Aghibalov on 08.05.2024
 */
class CardScreenViewModelTester {
    private lateinit var fakeCardList: List<Card>
    private lateinit var fakeCardRepository: CardRepository
    private lateinit var cardScreenViewModel: FlashcardsApplicationViewModel


    @Before
    fun setup() {
        fakeCardRepository = FakeCardRepository()
        cardScreenViewModel = CardScreenViewModel(fakeCardRepository)
        val fakeWord = "apple"
        val fakeTranslation = "яблоко"
        fakeCardList = listOf(Card(fakeWord, fakeTranslation))
    }


    @Test
    fun testCardListExtraction() {

        runTest {
            cardScreenViewModel.extractCardList()
        }

        assertEquals(true, cardScreenViewModel.hasList(fakeCardList))
    }
}