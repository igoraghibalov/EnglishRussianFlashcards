package com.example.englishrussianflashcards.appscreens.screenuielements.espresso

import com.example.englishrussianflashcards.ListItemSelectionTask
import com.example.englishrussianflashcards.appscreens.screenuielements.ClickableUi
import com.example.englishrussianflashcards.appscreens.screenuielements.ListItemSelectionUi

/**
 * Created by Igor Aghibalov on 23.02.2026
 */
class EspressoListItemSelectionUi(private val listItemSelectionTask: ListItemSelectionTask,
                                  private val listShowUi: ClickableUi)
    : ListItemSelectionUi {



    override fun showList() {
        listShowUi.click()
    }

    override fun selectItem() {
        listItemSelectionTask.run()
    }
}