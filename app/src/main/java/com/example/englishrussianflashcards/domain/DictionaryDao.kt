package com.example.englishrussianflashcards.domain

/**
 * Created by Igor Aghibalov on 26.05.2024
 */
interface DictionaryDao {
    fun getWordList(typedCharSequence: String): List<String>
    fun getTranslationList(word: String): List<String>
    fun getTranscription(word: String): String
    fun getExampleList(word: String, translation: String): List<String>
    fun getGroupTitleList(): List<String>
}