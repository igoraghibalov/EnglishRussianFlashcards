package com.example.englishrussianflashcards.screenuielements

/**
 * Created by Igor Aghibalov on 03.02.2026
 */
interface DroppableListItemSelectionUi<T: Any>: ListItemSelectionUi<T> {
    fun dropSelection()
}