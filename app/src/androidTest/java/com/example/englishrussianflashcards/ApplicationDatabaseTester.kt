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
    private lateinit var appleCard: Card
    private lateinit var earthCard: Card
    private lateinit var appleCardTranslation: String
    private lateinit var appleCardGroup: String
    private lateinit var appleCardTranscription: String
    private lateinit var appleCardWord: String
    private lateinit var cardDateFormatPattern: String
    private lateinit var userCharSequenceTyped: String
    private lateinit var planetGroupName: String
    private lateinit var fruitGroupName: String


    @Before
    fun setupDatabaseEnvironment() {
        val databaseContext = ApplicationProvider.getApplicationContext<Application>()
        val applicationResources = databaseContext.resources
        applicationDatabase = Room.inMemoryDatabaseBuilder(databaseContext, ApplicationDatabase::class.java).build()

        cardDao = applicationDatabase.getCardDao()
        dictionaryDao = applicationDatabase.getDictionaryDao()
        additionalTestCoroutineContext = Dispatchers.Default

        userCharSequenceTyped = applicationResources.getString(R.string.user_char_sequence_typed)
        cardDateFormatPattern = applicationResources.getString(R.string.card_date_format_pattern)

        planetGroupName = applicationResources.getString(R.string.planet_group_name)
        fruitGroupName = applicationResources.getString(R.string.fruit_group_name)

        appleCardWord = applicationResources.getString(R.string.apple_card_word)
        appleCardTranslation = applicationResources.getString(R.string.apple_card_translation)
        appleCardGroup = applicationResources.getString(R.string.apple_card_group)
        appleCardTranscription = applicationResources.getString(R.string.apple_card_transcription)

        appleCard = Card(id = applicationResources.getInteger(R.integer.apple_card_id),
                         word = appleCardWord,
                         translation = appleCardTranslation,
                         group = appleCardGroup,
                         transcription = appleCardTranscription,
                         isDisplayed = applicationResources.getBoolean(R.bool.is_apple_card_displayed))

        earthCard = Card(id = applicationResources.getInteger(R.integer.earth_card_id),
                         word = applicationResources.getString(R.string.earth_card_word),
                         translation = applicationResources.getString(R.string.earth_card_translation),
                         transcription = applicationResources.getString(R.string.earth_card_transcription),
                         group = planetGroupName,
                         isDisplayed = applicationResources.getBoolean(R.bool.is_apple_card_displayed))
    }


    @Test
    fun testCardListExtraction() {

        runBlocking {
            val fakeCardList = listOf(appleCard)
            val cardInsertionJob = launch(additionalTestCoroutineContext) { cardDao.insertCard(appleCard) }
            cardInsertionJob.join()
            val cardList = withContext(additionalTestCoroutineContext) { cardDao.getCardList() }
            assertEquals(true, cardList == fakeCardList)
        }
    }

    @Test
    fun testWordMapExtraction() {

        runBlocking {
            val date = LocalDate.now().format(DateTimeFormatter.ofPattern(cardDateFormatPattern))
            val postInsertionWordMap = mapOf(appleCardWord to date)
            val deferredWordMap: Deferred<Map<String, String>>

            val wordMapInsertionJob
                    = launch(additionalTestCoroutineContext) { cardDao.insertHistoryEntry(appleCardWord, date) }
            wordMapInsertionJob.join()

            deferredWordMap = async(additionalTestCoroutineContext) { cardDao.getWordMap() }

            assertEquals(postInsertionWordMap, deferredWordMap.await())
        }
    }


    @Test
    fun testCardGroupMapExtraction() {

        runBlocking {
            val cardGroupTitle = appleCardGroup
            val deferredCardGroupMap: Deferred<Map<String, List<Card>>>
            val fruitCardList = listOf(appleCard)
            val fruitCardGroupMap: Map<String, List<Card>> = mapOf(cardGroupTitle to fruitCardList)

            val wordMapInsertionJob = launch(additionalTestCoroutineContext) {
                cardDao.insertCard(appleCard)
            }
            wordMapInsertionJob.join()

            deferredCardGroupMap = async(additionalTestCoroutineContext) { cardDao.getCardGroupMap() }

            assertEquals(fruitCardGroupMap, deferredCardGroupMap.await())
        }
    }


    @Test
    fun testWordListExtraction() {
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
        val deferredTranslationList: Deferred<List<String>>

        runBlocking {

            deferredTranslationList = async(additionalTestCoroutineContext) {
                dictionaryDao.getTranslationList(appleCardWord)
            }

            assertEquals(false, deferredTranslationList.await().isEmpty())
        }
    }

    @Test
    fun testTranscriptionExtraction() {
        val deferredTranscription: Deferred<String>

        runBlocking {

            deferredTranscription = async(additionalTestCoroutineContext) {
                dictionaryDao.getTranscription(appleCardWord)
            }

            assertEquals(appleCardTranscription, deferredTranscription.await())
        }
    }

    @Test
    fun testExampleListExtraction() {
        val deferredExampleList: Deferred<List<String>>

        runBlocking {

            deferredExampleList = async(additionalTestCoroutineContext) {
                dictionaryDao.getExampleList(appleCardWord, appleCardTranslation)
            }

            assertEquals(false, deferredExampleList.await().isEmpty())
        }
    }

    @Test
    fun testGroupTitleListExtraction() {
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