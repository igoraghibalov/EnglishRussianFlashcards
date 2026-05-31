package com.example.englishrussianflashcards.appscreens.screenuielements.espresso

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import com.example.englishrussianflashcards.ListItemSelectionTask
import com.example.englishrussianflashcards.appscreens.screenuielements.AutoCompleteListItemSelectionUi
import com.example.englishrussianflashcards.appscreens.screenuielements.ClickableUi
import org.hamcrest.Matcher

/**
* Created by Igor Aghibalov on 07.02.2026
*/
class EspressoAutoCompleteListItemSelectionUi<T: Any>(private val listItemSelectionTask: ListItemSelectionTask,
                                                      private val characterTypeAction: ViewAction,
                                                      private val autoCompleteListItemSelectionUiMatcher: Matcher<View>,
                                                      private val textClearingUi: ClickableUi)
    : AutoCompleteListItemSelectionUi<T> {


    override fun typeCharacters() {
        onView(autoCompleteListItemSelectionUiMatcher).perform(characterTypeAction)
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

}
