package com.example.englishrussianflashcards.screenuielements

import com.example.englishrussianflashcards.ViewDataExtraction

/**
 * Created by Igor Aghibalov on 04.10.2025
 */
interface TextUi<T: Any>: ViewDataExtraction<T> {
    fun hasText()
}
