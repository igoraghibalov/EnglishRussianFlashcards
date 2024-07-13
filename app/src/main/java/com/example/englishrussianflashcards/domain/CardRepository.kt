package com.example.englishrussianflashcards.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Created by Igor Aghibalov on 05.06.2024
 */
class CardRepository(applicationDatabase: ApplicationDatabase,
                     private val coroutineDispatcher: CoroutineDispatcher): Repository(applicationDatabase),
                                                                            CardExtractor {
    private val cardDao: CardDao = (dataSource as ApplicationDatabase).getCardDao()

    override suspend fun getCardList(): List<Card> {

        return withContext(coroutineDispatcher) {
            cardDao.getCardList()
        }
    }

    override suspend fun getCardListByGroup(groupName: String): List<Card> {
        return withContext(coroutineDispatcher) {
            cardDao.getCardListByGroup(groupName)
        }
    }


    override suspend fun getCardWordMap(): Map<String, String> {

        return withContext(coroutineDispatcher) {
            cardDao.getWordMap()
        }
    }


    override suspend fun getCardGroupMap(): Map<String, List<Card>> {
        return withContext(coroutineDispatcher) {
            cardDao.getCardGroupMap()
        }
    }


    override suspend fun getGroupTitleList(): List<String> {
        return withContext(coroutineDispatcher) {
            cardDao.getGroupTitleList()
        }
    }
}