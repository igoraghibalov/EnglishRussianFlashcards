package com.example.englishrussianflashcards.presentation

import com.example.englishrussianflashcards.domain.LiveDataWrapper
import com.example.englishrussianflashcards.domain.Repository

/**
 * Created by Igor Aghibalov on 01.08.2024
 */
class CardCreationViewModel(private val liveDataWrapper: LiveDataWrapper<*>,
                            private val dictionaryRepository: Repository,
                            private val imageRepository: Repository) {

    fun fetchTranslationList(typedWord: String) {

    }

    fun fetchExample() {}
    fun fetchTranscriptionList() {}
    fun fetchImageList() {}
    fun fetchGroupTitleList() {}
}


fun testTranslationExtraction() {

}