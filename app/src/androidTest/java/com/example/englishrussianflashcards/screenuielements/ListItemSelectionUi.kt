package com.example.englishrussianflashcards.screenuielements

import com.example.englishrussianflashcards.ViewDataExtraction

/**
 * Created by Igor Aghibalov on 03.02.2026
 */
interface ListItemSelectionUi: ViewDataExtraction<String> {
    fun showList()
    fun selectItem()
}