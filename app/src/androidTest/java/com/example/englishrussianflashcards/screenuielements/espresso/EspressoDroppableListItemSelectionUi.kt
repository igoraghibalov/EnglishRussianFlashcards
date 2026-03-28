package com.example.englishrussianflashcards.screenuielements.espresso

import androidx.recyclerview.widget.RecyclerView
import com.example.englishrussianflashcards.ListItemSelectionTask
import com.example.englishrussianflashcards.RecyclerViewItemSelectionTask
import com.example.englishrussianflashcards.ViewDataExtraction
import com.example.englishrussianflashcards.screenuielements.ClickableUi
import com.example.englishrussianflashcards.screenuielements.DroppableListItemSelectionUi

/**
 * Created by Igor Aghibalov on 28.02.2026
 */
class EspressoDroppableListItemSelectionUi<T: Any, VH: RecyclerView.ViewHolder>(
                private val selectionDropUi: ClickableUi,
                private val showListUi: ClickableUi,
                private val itemSelectionTask: RecyclerViewItemSelectionTask<VH>,
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