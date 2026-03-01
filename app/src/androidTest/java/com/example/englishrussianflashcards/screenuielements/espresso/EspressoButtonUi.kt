package com.example.englishrussianflashcards.screenuielements.espresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.screenuielements.ButtonUi
import com.example.englishrussianflashcards.screenuielements.ClickableUi

/**
 * Created by Igor Aghibalov on 11.10.2025
 */
class EspressoButtonUi(private val clickableButtonUi: ClickableUi): ButtonUi {

    override fun click() {
        clickableButtonUi.click()
    }
}