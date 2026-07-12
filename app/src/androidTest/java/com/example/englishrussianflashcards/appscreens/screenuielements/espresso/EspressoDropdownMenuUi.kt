package com.example.englishrussianflashcards.appscreens.screenuielements.espresso

import com.example.englishrussianflashcards.MenuItemSelectionTask
import com.example.englishrussianflashcards.appscreens.screenuielements.ClickableUi
import com.example.englishrussianflashcards.appscreens.screenuielements.DropdownMenuUi

/**
 * Created by Igor Aghibalov on 23.02.2026
 */
class EspressoDropdownMenuUi(private val menuItemSelectionTask: MenuItemSelectionTask,
                             private val dropdownMenuShowUi: ClickableUi)
    : DropdownMenuUi {



    override fun showList() {
        dropdownMenuShowUi.click()
    }

    override fun selectItem() {
        menuItemSelectionTask.run()
    }

    override fun checkItemPresence() {
        selectItem()
    }

    override fun scrollToItem() {
        selectItem()
    }
}