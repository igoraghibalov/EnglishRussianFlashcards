package com.example.englishrussianflashcards.appscreens.screenuielements.espresso

import com.example.englishrussianflashcards.appscreens.screenuielements.ButtonUi
import com.example.englishrussianflashcards.appscreens.screenuielements.ClickableUi

/**
 * Created by Igor Aghibalov on 11.10.2025
 */
class EspressoButtonUi(private val clickableButtonUi: ClickableUi): ButtonUi {

    override fun click() {
        clickableButtonUi.click()
    }
}