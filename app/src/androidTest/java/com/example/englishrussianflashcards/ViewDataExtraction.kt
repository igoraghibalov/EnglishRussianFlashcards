package com.example.englishrussianflashcards

/**
 * Created by Igor Aghibalov on 02.11.2025
 */
interface ViewDataExtraction<T: Any> {
    fun extractViewData(): T
}