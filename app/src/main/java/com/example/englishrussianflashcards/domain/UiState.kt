package com.example.englishrussianflashcards.domain

/**
 * Created by Igor Aghibalov on 31.07.2024
 */
interface UiState<T: Any> {
    fun show(uiStateShowHandler: UiStateShowHandler<T>)
}


class Success<T: Any>(private val value: T): UiState<T> {

    override fun show(uiStateShowHandler: UiStateShowHandler<T>) {
        uiStateShowHandler.handle(value)
    }
}


class Error<T: Throwable>(private val value: T): UiState<T> {

    override fun show(uiStateShowHandler: UiStateShowHandler<T>) {
        uiStateShowHandler.handle(value)
    }
}


class Loading(private val value: CharSequence): UiState<CharSequence> {

    override fun show(uiStateShowHandler: UiStateShowHandler<CharSequence>) {
        uiStateShowHandler.handle(value)
    }
}