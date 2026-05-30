package com.example.englishrussianflashcards.appscreens.screenuielements.espresso

import com.example.englishrussianflashcards.ListItemSelectionTask
import com.example.englishrussianflashcards.ViewDataExtraction
import com.example.englishrussianflashcards.appscreens.screenuielements.ClickableUi
import com.example.englishrussianflashcards.appscreens.screenuielements.ListItemSelectionUi

/**
 * Created by Igor Aghibalov on 23.02.2026
 */
class EspressoListItemSelectionUi<T: Any>(private val listItemSelectionTask: ListItemSelectionTask,
                                          private val viewDataExtraction: ViewDataExtraction<T>,
                                          private val listShowUi: ClickableUi)
    : ListItemSelectionUi<T> {



    override fun showList() {
        listShowUi.click()
    }

    override fun selectItem() {
        listItemSelectionTask.run()
    }

    override fun extractViewData(): T {
        return viewDataExtraction.extractViewData()
    }
}