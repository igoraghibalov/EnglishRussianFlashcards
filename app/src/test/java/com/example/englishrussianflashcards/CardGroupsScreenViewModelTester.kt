package com.example.englishrussianflashcards

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Igor Aghibalov on 10.05.2024
 */
class CardGroupsScreenViewModelTester {
    private lateinit var fakeCardGroupMap: Map<String, List<Card>>
    private lateinit var fakeCardRepository: CardRepository
    private lateinit var mainScreenViewModel: FlashcardsApplicationViewModel


    @Before
    fun setup() {
        fakeCardRepository = FakeCardRepository()
        mainScreenViewModel = CardGroupsScreenViewModel(fakeCardRepository)
        val fakeWord = "apple"
        val fakeTranslation = "яблоко"
        fakeCardGroupMap = mapOf("Fruits" to Card(fakeWord, fakeTranslation))
    }



    @Test
    fun testCardsInGroupsExtraction() {

        runTest {
            cardGroupsScreenViewModel.extractCardsInGroups()
        }
        assertEquals(true, cardGroupsScreenViewModel.hasCardGroupMap(fakeCardGroupMap))
    }
}