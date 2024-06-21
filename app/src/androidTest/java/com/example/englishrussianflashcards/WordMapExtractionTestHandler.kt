package com.example.englishrussianflashcards

import com.example.englishrussianflashcards.domain.CardDao
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.coroutines.CoroutineContext

/**
 * Created by Igor Aghibalov on 21.06.2024
 */
class WordMapExtractionTestHandler(private val cardDateFormatPattern: String,
                                   private val cardWord: String,
                                   private val additionalTestCoroutineContext: CoroutineContext,
                                   private val cardDao: CardDao): CaseUiTestHandler() {


    override fun handleCaseTest() {

        runBlocking {
            val date = LocalDate.now().format(DateTimeFormatter.ofPattern(cardDateFormatPattern))
            val postInsertionWordMap = mapOf(cardWord to date)
            val deferredWordMap: Deferred<Map<String, String>>

            val wordMapInsertionJob
                    = launch(additionalTestCoroutineContext) { cardDao.insertHistoryEntry(cardWord, date) }
            wordMapInsertionJob.join()

            deferredWordMap = async(additionalTestCoroutineContext) { cardDao.getWordMap() }

            Assert.assertEquals(postInsertionWordMap, deferredWordMap.await())
        }
    }

    override fun doAfterCaseTestHandling() {}
}