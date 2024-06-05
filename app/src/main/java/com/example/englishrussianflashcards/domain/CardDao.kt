package com.example.englishrussianflashcards.domain

/**
 * Created by Igor Aghibalov on 26.05.2024
 */
interface CardDao {
    fun insertCard(card: Card)
    fun getCardList(): List<Card>
    fun insertHistoryEntry(word: String, date: String)
    fun getWordMap(): Map<String, String>
    fun getCardGroupMap(): Map<String, List<Card>>
    fun getGroupTitleList(): List<String>
}