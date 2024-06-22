package com.example.englishrussianflashcards.casetesthandlers

import com.example.englishrussianflashcards.CaseUiTestHandler
import com.example.englishrussianflashcards.domain.Card
import com.example.englishrussianflashcards.domain.CardDao
import com.example.englishrussianflashcards.domain.DictionaryDao
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import kotlin.coroutines.CoroutineContext

/**
 * Created by Igor Aghibalov on 22.06.2024
 */
class GroupTitleListExtractionTestHandler(private val expectedGroupTitlesPair: Pair<String, String>,
                                          private val cardPairWithGroupTitles: Pair<Card, Card>,
                                          private val cardDao: CardDao,
                                          private val dictionaryDao: DictionaryDao,
                                          private val additionalTestCoroutineContext: CoroutineContext)
    : CaseUiTestHandler() {


    override fun handleCaseTest() {
        val deferredGroupTitleList: Deferred<List<String>>

        runBlocking {

            val cardInsertionJob = launch(additionalTestCoroutineContext){
                cardDao.insertCard(cardPairWithGroupTitles.first)
                launch { cardDao.insertCard(cardPairWithGroupTitles.second) }
            }
            cardInsertionJob.join()

            deferredGroupTitleList = async(additionalTestCoroutineContext) {
                dictionaryDao.getGroupTitleList()
            }

            Assert.assertEquals(expectedGroupTitlesPair.toList(), deferredGroupTitleList.await())
        }
    }


    override fun doAfterCaseTestHandling() {}
}