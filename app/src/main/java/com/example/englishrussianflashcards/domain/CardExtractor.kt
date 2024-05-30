package com.example.englishrussianflashcards.domain

/**
 * Created by Igor Aghibalov on 30.05.2024
 */
interface CardExtractor {
    fun getCardList(): List<Card>
    fun getCardWordMap(): Map<String, String>
    fun getCardGroupMap(): Map<String, List<Card>>
}