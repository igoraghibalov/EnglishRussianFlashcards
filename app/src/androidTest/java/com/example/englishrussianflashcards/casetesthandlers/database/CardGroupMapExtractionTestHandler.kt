package com.example.englishrussianflashcards.casetesthandlers.database

import com.example.englishrussianflashcards.CaseUiTestHandler
import com.example.englishrussianflashcards.domain.Card
import com.example.englishrussianflashcards.domain.CardDao
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import kotlin.coroutines.CoroutineContext

/**
 * Created by Igor Aghibalov on 22.06.2024
 */
//TODO: move to unit tests folder
class CardGroupMapExtractionTestHandler(private val cardGroupTitle: String,
                                        private val card: Card,
                                        private val additionalTestCoroutineContext: CoroutineContext,
                                        private val cardDao: CardDao)
    : CaseUiTestHandler() {


    override fun handleCaseTest() {

        runBlocking {
            val deferredCardGroupMap: Deferred<Map<String, List<Card>>>
            val fruitCardList = listOf(card)
            val fruitCardGroupMap: Map<String, List<Card>> = mapOf(cardGroupTitle to fruitCardList)

            val wordMapInsertionJob = launch(additionalTestCoroutineContext) {
                cardDao.insertCard(card)
            }
            wordMapInsertionJob.join()

            deferredCardGroupMap = async(additionalTestCoroutineContext) { cardDao.getCardGroupMap() }

            assertEquals(fruitCardGroupMap, deferredCardGroupMap.await())
        }
    }
}