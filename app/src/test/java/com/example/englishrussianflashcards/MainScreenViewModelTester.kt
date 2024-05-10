package com.example.englishrussianflashcards

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.runner.AndroidJUnitRunner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
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
    private lateinit var fakeCardRepository: Repository
    private lateinit var mainScreenViewModel: FlashcardsApplicationViewModel


    @Before
    fun setup() {
        val fakeCardRepository = FakeCardRepository()
        val mainScreenViewModel = MainScreenViewModel(fakeCardRepository)
        val fakeWord = "apple"
        val fakeTranslation = "яблоко"
        fakeCardList = listOf(Card(fakeWord, fakeTranslation))
    }


    @Test
    fun testCardListExtraction() {

        runTest {
            mainScreenViewModel.extractCardList()
        }

        assertEquals(true, mainScreenViewModel.hasList(fakeCardList))
    }
}