package com.example.englishrussianflashcards

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.runner.AndroidJUnitRunner
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Igor Aghibalov on 08.05.2024
 */
class MainScreenViewModelTester {
    private lateinit var fakeCardList: List<Card>

    @Before
    fun setup() {
        val fakeWord = "apple"
        val fakeTranslation = "яблоко"
        fakeCardList = listOf(Card(fakeWord, fakeTranslation))
    }


    @Test
    fun testCardListExtraction() {
        val fakeCardRepository = FakeCardRepository()
        val mainScreenViewModel = MainScreenViewModel(fakeCardRepository)

        //TODO: use coroutines
        mainScreenViewModel.extractCardList()

        assertEquals(true, mainScreenViewModel.hasList(fakeCardList))
    }
}