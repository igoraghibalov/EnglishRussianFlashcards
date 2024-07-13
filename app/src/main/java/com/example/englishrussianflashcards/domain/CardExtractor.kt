package com.example.englishrussianflashcards.domain

/**
 * Created by Igor Aghibalov on 30.05.2024
 */
interface CardExtractor {
    suspend fun getCardList(): List<Card>
    suspend fun getCardListByGroup(groupName: String): List<Card>
    suspend fun getCardWordMap(): Map<String, String>
    suspend fun getCardGroupMap(): Map<String, List<Card>>
    suspend fun getGroupTitleList(): List<String>
}