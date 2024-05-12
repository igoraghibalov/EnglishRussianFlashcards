package com.example.englishrussianflashcards

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Igor Aghibalov on 10.05.2024
 */
class CardGroupsScreenViewModelTester: ViewModelTester() {
    private lateinit var fakeCardGroupMap: Map<String, List<Card>>


    @Before
    override fun setup() {
        fakeCardRepository = FakeCardRepository()
        viewModel = CardGroupsScreenViewModel(fakeCardRepository)
        val fakeWord = "apple"
        val fakeTranslation = "яблоко"
        fakeCardGroupMap = mapOf("Fruits" to Card(fakeWord, fakeTranslation))
    }


    @Test
    fun testCardsInGroupsExtraction() {

        runTest {
            viewModel.extractCardsInGroups()
        }
        assertEquals(true, viewModel.hasCardGroupMap(fakeCardGroupMap))
    }
}