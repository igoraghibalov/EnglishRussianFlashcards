package com.example.englishrussianflashcards

import android.app.Application
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.englishrussianflashcards.domain.ApplicationDatabase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.coroutines.CoroutineContext
import com.example.englishrussianflashcards.domain.Card
import com.example.englishrussianflashcards.domain.CardDao
import com.example.englishrussianflashcards.domain.DictionaryDao

/**
 * Created by Igor Aghibalov on 20.05.2024
 */
@RunWith(AndroidJUnit4::class)
class ApplicationDatabaseTester {
    private lateinit var applicationDatabase: ApplicationDatabase
    private lateinit var cardDao: CardDao
    private lateinit var dictionaryDao: DictionaryDao
    private lateinit var additionalTestCoroutineContext: CoroutineContext


    @Before
    fun setupDatabaseEnvironment() {
        val databaseContext = ApplicationProvider.getApplicationContext<Application>()
        applicationDatabase = Room.inMemoryDatabaseBuilder(databaseContext, ApplicationDatabase::class.java).build()
        cardDao = applicationDatabase.getCardDao()
        dictionaryDao = applicationDatabase.getDictionaryDao()
        additionalTestCoroutineContext = Dispatchers.Default
    }


    @Test
    fun testCardListExtraction() {

        runBlocking {
            val fakeWord = "apple"
            val fakeTranslation = "яблоко"
            val isDisplayed = true
            val fakeCard = Card(fakeWord, fakeTranslation, isDisplayed = isDisplayed)
            val fakeCardList = listOf(fakeCard)

            val cardInsertionJob = launch(additionalTestCoroutineContext) { cardDao.insertCard(fakeCard) }
            cardInsertionJob.join()
            val cardList = withContext(additionalTestCoroutineContext) { cardDao.getCardList() }
            assertEquals(true, cardList == fakeCardList)
        }
    }

    @Test
    fun testWordMapExtraction() {

        runBlocking {
            val word = "apple"
            val date = LocalDate.now().format(DateTimeFormatter.ofPattern("d.MM.yyyy"))
            val postInsertionWordMap = mapOf(word to date)
            val deferredWordMap: Deferred<Map<String, String>>

            val wordMapInsertionJob
                    = launch(additionalTestCoroutineContext) { cardDao.insertHistoryEntry(word, date) }
            wordMapInsertionJob.join()

            deferredWordMap = async(additionalTestCoroutineContext) { cardDao.getWordMap() }

            assertEquals(postInsertionWordMap, deferredWordMap.await())
        }
    }


    @Test
    fun testCardGroupMapExtraction() {

        runBlocking {
            val deferredCardGroupMap: Deferred<Map<String, List<Card>>>
            val cardGroupTitle = "Fruits"
            val appleCard = Card("apple", "яблоко", cardGroupTitle)
            val orangeCard = Card("orange", "апельсин", cardGroupTitle)
            val fruitCardList = listOf(appleCard, orangeCard)
            val fruitCardGroupMap: Map<String, List<Card>> = mapOf(cardGroupTitle to fruitCardList)

            val wordMapInsertionJob = launch(additionalTestCoroutineContext) {
                cardDao.insertCard(appleCard)
                launch { cardDao.insertCard(orangeCard) }
            }
            wordMapInsertionJob.join()

            deferredCardGroupMap = async(additionalTestCoroutineContext) { cardDao.getCardGroupMap() }

            assertEquals(fruitCardGroupMap, deferredCardGroupMap.await())
        }
    }


    @Test
    fun testWordListExtraction() {
        val userCharSequenceTyped = "ab"
        val deferredWordList: Deferred<List<String>>

        runBlocking {

            deferredWordList = async(additionalTestCoroutineContext) {
                dictionaryDao.getWordList(userCharSequenceTyped)
            }

            assertEquals(false, deferredWordList.await().isEmpty())
        }
    }

    @Test
    fun testTranslationListExtraction() {
        val word = "apple"
        val deferredTranslationList: Deferred<List<String>>

        runBlocking {

            deferredTranslationList = async(additionalTestCoroutineContext) {
                dictionaryDao.getTranslationList(word)
            }

            assertEquals(false, deferredTranslationList.await().isEmpty())
        }
    }

    @Test
    fun testTranscriptionExtraction() {
        val word = "apple"
        val wordTranscriptionToCompare = "[ˈæpəl]"
        val deferredTranscription: Deferred<String>

        runBlocking {

            deferredTranscription = async(additionalTestCoroutineContext) {
                dictionaryDao.getTranscription(word)
            }

            assertEquals(wordTranscriptionToCompare, deferredTranscription.await())
        }
    }

    @Test
    fun testExampleListExtraction() {
        val word = "apple"
        val translation = "яблоко"
        val deferredExampleList: Deferred<List<String>>

        runBlocking {

            deferredExampleList = async(additionalTestCoroutineContext) {
                dictionaryDao.getExampleList(word, translation)
            }

            assertEquals(false, deferredExampleList.await().isEmpty())
        }
    }

    @Test
    fun testGroupTitleListExtraction() {
        val planetGroupName = "Planets"
        val fruitGroupName = "Fruits"
        val appleCard = Card(word = "apple", translation = "яблоко", group = planetGroupName)
        val earthCard = Card(word = "Earth", translation = "Земля", group = fruitGroupName)
        val deferredGroupTitleList: Deferred<List<String>>
        val groupTitleListToCompare = listOf(planetGroupName, fruitGroupName)

        runBlocking {

            val cardInsertionJob = launch(additionalTestCoroutineContext){
                cardDao.insertCard(appleCard)
                launch { cardDao.insertCard(earthCard) }
            }
            cardInsertionJob.join()

            deferredGroupTitleList = async(additionalTestCoroutineContext) {
                dictionaryDao.getGroupTitleList()
            }

            assertEquals(groupTitleListToCompare, deferredGroupTitleList.await())
        }
    }


    @After
    fun closeDatabase() {
        applicationDatabase.close()
    }
}