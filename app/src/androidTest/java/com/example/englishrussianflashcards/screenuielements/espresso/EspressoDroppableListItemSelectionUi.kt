package com.example.englishrussianflashcards.screenuielements.espresso

import com.example.englishrussianflashcards.ListItemSelectionTask
import com.example.englishrussianflashcards.ViewDataExtraction
import com.example.englishrussianflashcards.screenuielements.ClickableUi
import com.example.englishrussianflashcards.screenuielements.DroppableListItemSelectionUi

/**
 * Created by Igor Aghibalov on 28.02.2026
 */
class EspressoDroppableListItemSelectionUi<T: Any>(private val selectionDropUi: ClickableUi,
                                                   private val showListUi: ClickableUi,
                                                   private val itemSelectionTask: ListItemSelectionTask,
                                                   private val selectedItemExtraction: ViewDataExtraction<T>)
    : DroppableListItemSelectionUi<T> {


    override fun dropSelection() {
        selectionDropUi.click()
    }

    override fun showList() {
        showListUi.click()
    }

    override fun selectItem() {
        itemSelectionTask.run()
    }

    override fun extractViewData(): T {
        return selectedItemExtraction.extractViewData()
    }
}