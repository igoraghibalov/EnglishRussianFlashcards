package com.example.englishrussianflashcards.domain

/**
 * Created by Igor Aghibalov on 26.05.2024
 */
interface CardDao {
    suspend fun insertCard(card: Card)
    suspend fun getCardList(): List<Card>
    suspend fun getCardListByGroup(groupName: String): List<Card>
    suspend fun insertHistoryEntry(word: String, date: String)
    suspend fun getWordMap(): Map<String, String>
    suspend fun getCardGroupMap(): Map<String, List<Card>>
    suspend fun getGroupTitleList(): List<String>
}