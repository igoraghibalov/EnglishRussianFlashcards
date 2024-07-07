package com.example.englishrussianflashcards.testers

import android.app.Application
import android.content.res.Resources
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.englishrussianflashcards.R
import com.example.englishrussianflashcards.casetesthandlers.database.CardGroupMapExtractionTestHandler
import com.example.englishrussianflashcards.casetesthandlers.database.CardListExtractionTestHandler
import com.example.englishrussianflashcards.casetesthandlers.database.DictionaryEntryExtractionTestHandler
import com.example.englishrussianflashcards.casetesthandlers.database.GroupTitleListExtractionTestHandler
import com.example.englishrussianflashcards.casetesthandlers.database.WordMapExtractionTestHandler
import com.example.englishrussianflashcards.domain.ApplicationDatabase
import kotlinx.coroutines.Dispatchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.example.englishrussianflashcards.domain.Card
import com.example.englishrussianflashcards.domain.CardDao
import com.example.englishrussianflashcards.domain.DictionaryDao

/**
 * Created by Igor Aghibalov on 20.05.2024
 */
@RunWith(AndroidJUnit4::class)
class ApplicationDatabaseTester: DatabaseTester() {
    private lateinit var cardDao: CardDao
    private lateinit var dictionaryDao: DictionaryDao
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
    private lateinit var applicationResources: Resources


    @Before
    override fun setupTestEnvironment() {
        val databaseContext = ApplicationProvider.getApplicationContext<Application>()
        applicationResources = databaseContext.resources
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
        appleCardGroup = applicationResources.getString(R.string.fruit_group_name)
        appleCardTranscription = applicationResources.getString(R.string.apple_card_transcription)

        appleCard = Card(id = applicationResources.getInteger(R.integer.apple_card_id),
                         word = appleCardWord,
                         translation = appleCardTranslation,
                         group = appleCardGroup,
                         transcription = appleCardTranscription,
                         isDisplayed = applicationResources.getBoolean(R.bool.is_apple_card_displayed))
    }


    @Test
    fun testCardListExtraction() {
        testCase(caseTestHandler = CardListExtractionTestHandler(appleCard,
                                                                 additionalTestCoroutineContext,
                                                                 cardDao)
        )
    }


    @Test
    fun testWordMapExtraction() {
        testCase(caseTestHandler = WordMapExtractionTestHandler(cardDateFormatPattern,
                                                                appleCardWord,
                                                                additionalTestCoroutineContext,
                                                                cardDao)
        )
    }


    @Test
    fun testCardGroupMapExtraction() {
        testCase(caseTestHandler = CardGroupMapExtractionTestHandler(appleCardGroup,
                                                                     appleCard,
                                                                     additionalTestCoroutineContext,
                                                                     cardDao)
        )
    }


    @Test
    fun testDictionaryEntryExtraction() {
        testCase(caseTestHandler = DictionaryEntryExtractionTestHandler(additionalTestCoroutineContext,
                                                                        dictionaryDao,
                                                                        userCharSequenceTyped,
                                                                        appleCardWord,
                                                                        appleCardTranscription,
                                                                        appleCardTranslation)
        )
    }


    @Test
    fun testGroupTitleListExtraction() {
        earthCard = Card(id = applicationResources.getInteger(R.integer.earth_card_id),
                         word = applicationResources.getString(R.string.earth_card_word),
                         translation = applicationResources.getString(R.string.earth_card_translation),
                         transcription = applicationResources.getString(R.string.earth_card_transcription),
                         group = planetGroupName,
                         isDisplayed = applicationResources.getBoolean(R.bool.is_apple_card_displayed))

        testCase(caseTestHandler = GroupTitleListExtractionTestHandler(expectedGroupTitlesPair = Pair(planetGroupName, fruitGroupName),
                                                                       cardPairWithGroupTitles = Pair(appleCard, earthCard),
                                                                       cardDao,
                                                                       dictionaryDao,
                                                                       additionalTestCoroutineContext)
        )
    }


    @After
    fun closeDatabase() {
        applicationDatabase.close()
    }
}