package com.example.englishrussianflashcards.screenuielements.espresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.ListItemSelectionTask
import com.example.englishrussianflashcards.ViewDataExtraction
import com.example.englishrussianflashcards.screenuielements.AutoCompleteListItemSelectionUi
import com.example.englishrussianflashcards.screenuielements.ClickableUi

/**
* Created by Igor Aghibalov on 07.02.2026
*/
class EspressoAutoCompleteListItemSelectionUi<T: Any>(private val listItemSelectionTask: ListItemSelectionTask,
                                                      private val typingCharacters: String,
                                                      private val listShowTriggerId: Int,
                                                      private val wordExtraction: ViewDataExtraction<T>,
                                                      private val textClearingUi: ClickableUi)
    : AutoCompleteListItemSelectionUi<T> {


    override fun typeCharacters() {
        onView(withId(listShowTriggerId)).perform(typeText(typingCharacters))
    }

    override fun clearText() {
        textClearingUi.click()
    }

    override fun showList() {
        typeCharacters()
    }

    override fun selectItem() {
        listItemSelectionTask.run()
    }

    override fun extractViewData(): T = wordExtraction.extractViewData()
}
