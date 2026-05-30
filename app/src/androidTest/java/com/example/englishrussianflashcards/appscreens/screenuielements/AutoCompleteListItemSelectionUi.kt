package com.example.englishrussianflashcards.appscreens.screenuielements

/**
 * Created by Igor Aghibalov on 03.02.2026
 */
interface AutoCompleteListItemSelectionUi<T: Any>: ListItemSelectionUi<T> {
    fun typeCharacters()
    fun clearText()
}