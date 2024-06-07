package com.example.englishrussianflashcards.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Igor Aghibalov on 26.05.2024
 */
@Entity
data class Card(@PrimaryKey val id: Int,
                val word: String,
                val translation: String,
                val transcription: String,
                val group: String,
                val isDisplayed: Boolean) {
}