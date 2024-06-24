package com.example.englishrussianflashcards.casetesthandlers

import com.example.englishrussianflashcards.CaseTestHandler
import com.example.englishrussianflashcards.domain.Card
import com.example.englishrussianflashcards.domain.CardDao
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Assert.assertEquals
import kotlin.coroutines.CoroutineContext

/**
 * Created by Igor Aghibalov on 19.06.2024
 */
class CardListExtractionTestHandler(private val card: Card,
                                    private val additionalTestCoroutineContext: CoroutineContext,
                                    private val cardDao: CardDao):
    CaseTestHandler {


    override fun handleCaseTest() {

        runBlocking {
            val expectedCardList = listOf(card)
            val cardInsertionJob = launch(additionalTestCoroutineContext) { cardDao.insertCard(card) }
            cardInsertionJob.join()
            val cardList = withContext(additionalTestCoroutineContext) { cardDao.getCardList() }
            assertEquals(expectedCardList, cardList)
        }
    }

    override fun doAfterCaseTestHandling() {}
}