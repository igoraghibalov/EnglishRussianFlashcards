package com.example.englishrussianflashcards.appscreens.screenuielements.espresso

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import com.example.englishrussianflashcards.MenuItemSelectionTask
import com.example.englishrussianflashcards.appscreens.screenuielements.AutoCompleteDropdownMenuUi
import com.example.englishrussianflashcards.appscreens.screenuielements.ClickableUi
import org.hamcrest.Matcher

/**
* Created by Igor Aghibalov on 07.02.2026
*/
class EspressoAutoCompleteDropdownMenuUi(private val dropdownMenuItemSelectionTask: MenuItemSelectionTask,
                                         private val characterTypeAction: ViewAction,
                                         private val autoCompleteDropdownMenuUiMatcher: Matcher<View>,
                                         private val textClearingUi: ClickableUi)
    : AutoCompleteDropdownMenuUi {


    override fun typeCharacters() {
        onView(autoCompleteDropdownMenuUiMatcher).perform(characterTypeAction)
    }

    override fun clearText() {
        textClearingUi.click()
    }

    override fun showMenu() {
        typeCharacters()
    }

    override fun selectItem() {
        dropdownMenuItemSelectionTask.run()
    }

    override fun checkItemPresence() {
        selectItem()
    }

    override fun scrollToItem() {
        selectItem()
    }
}
