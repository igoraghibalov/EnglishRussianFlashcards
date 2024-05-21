package com.example.englishrussianflashcards

import android.app.Application
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.Throws

/**
 * Created by Igor Aghibalov on 20.05.2024
 */
// TODO: add DictionaryDao testing
@RunWith(AndroidJUnit4::class)
class ApplicationDatabaseTester {
    private lateinit var applicationDatabase: ApplicationDatabase
    private lateinit var cardDao: CardDao
    private lateinit var additionalTestCoroutineContext: CoroutineContext


    @Before
    fun setupDatabaseEnvironment() {
        val databaseContext = ApplicationProvider.getApplicationContext<Application>()
        applicationDatabase = Room.inMemoryDatabaseBuilder(databaseContext, ApplicationDatabase::class.java).build()
        cardDao = applicationDatabase.getCardDao()
        additionalTestCoroutineContext = Dispatchers.Default
    }

    @Test
    fun testSuccessCardListExtraction() {

        runBlocking {
            val fakeWord = "apple"
            val fakeTranslation = "яблоко"
            val isDisplayed = true
            val fakeCard = Card(fakeWord, fakeTranslation, isDisplayed)
            val fakeCardList = listOf(fakeCard)

            val cardInsertionJob = launch(additionalTestCoroutineContext) { cardDao.insertCard(fakeCard) }
            cardInsertionJob.join()
            val cardList = withContext(additionalTestCoroutineContext) { cardDao.getCardList() }
            assertEquals(true, cardList == fakeCardList)
        }
    }


    @After
    fun closeDatabase() {
        applicationDatabase.close()
    }
}