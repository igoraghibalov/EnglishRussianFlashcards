package com.example.englishrussianflashcards

import kotlinx.serialization.Serializable

/**
 * Created by Igor Aghibalov on 12.10.2025
 */
@Serializable
data class Card(val word: String,
                val transcription: String,
                val translation: String,
                val example: String,
                val imageTag: String,
                val cardGroupName: String)