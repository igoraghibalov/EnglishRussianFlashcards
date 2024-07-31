package com.example.englishrussianflashcards.domain

/**
 * Created by Igor Aghibalov on 31.07.2024
 */
interface UiStateShowHandler<T: Any> {
    fun handle(value: T)
}