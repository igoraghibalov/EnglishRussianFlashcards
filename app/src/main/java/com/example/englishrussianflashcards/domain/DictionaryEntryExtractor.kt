package com.example.englishrussianflashcards.domain

/**
 * Created by Igor Aghibalov on 30.05.2024
 */
interface DictionaryEntryExtractor {
    fun getWordList(): List<String>
    fun getTranslationList(): List<String>
    fun getTranscription(): String
    fun getExampleList(): List<String>
}