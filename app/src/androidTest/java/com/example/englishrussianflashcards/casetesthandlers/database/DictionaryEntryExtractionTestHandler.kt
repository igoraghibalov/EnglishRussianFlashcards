package com.example.englishrussianflashcards.casetesthandlers.database

import com.example.englishrussianflashcards.CaseUiTestHandler
import com.example.englishrussianflashcards.domain.DictionaryDao
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import kotlin.coroutines.CoroutineContext

/**
 * Created by Igor Aghibalov on 22.06.2024
 */
class DictionaryEntryExtractionTestHandler(private val additionalTestCoroutineContext: CoroutineContext,
                                           private val dictionaryDao: DictionaryDao,
                                           private val userCharSequenceTyped: String,
                                           private val cardWord: String,
                                           private val cardTranscription: String,
                                           private val cardTranslation: String)
    : CaseUiTestHandler() {


    override fun handleCaseTest() {
        handleWordListExtractionTest()
        handleTranslationListExtractionTest()
        handleTranscriptionExtractionTest()
        handleExampleListExtractionTest()
    }

    fun handleExampleListExtractionTest() {
        val deferredExampleList: Deferred<List<String>>

        runBlocking {

            deferredExampleList = async(additionalTestCoroutineContext) {
                dictionaryDao.getExampleList(cardWord, cardTranslation)
            }

            Assert.assertEquals(false, deferredExampleList.await().isEmpty())
        }
    }

    fun handleTranscriptionExtractionTest() {
        val deferredTranscription: Deferred<String>

        runBlocking {

            deferredTranscription = async(additionalTestCoroutineContext) {
                dictionaryDao.getTranscription(cardWord)
            }

            assertEquals(
                cardTranscription,
                deferredTranscription.await()
            )
        }
    }

    fun handleTranslationListExtractionTest() {
        val deferredTranslationList: Deferred<List<String>>

        runBlocking {

            deferredTranslationList = async(additionalTestCoroutineContext) {
                dictionaryDao.getTranslationList(cardWord)
            }

            Assert.assertEquals(
                false,
                deferredTranslationList.await().isEmpty()
            )
        }
    }


    fun handleWordListExtractionTest() {
        val deferredWordList: Deferred<List<String>>

        runBlocking {

            deferredWordList = async(additionalTestCoroutineContext) {
                dictionaryDao.getWordList(userCharSequenceTyped)
            }

            Assert.assertEquals(false, deferredWordList.await().isEmpty())
        }
    }

    override fun doAfterCaseTestHandling() {}
}