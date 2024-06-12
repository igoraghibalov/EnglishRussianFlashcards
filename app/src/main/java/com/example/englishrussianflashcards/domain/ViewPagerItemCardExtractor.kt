package com.example.englishrussianflashcards.domain

/**
 * Created by Igor Aghibalov on 12.06.2024
 */
interface ViewPagerItemCardExtractor {

    suspend fun getViewPagerItemCard(): Card
}