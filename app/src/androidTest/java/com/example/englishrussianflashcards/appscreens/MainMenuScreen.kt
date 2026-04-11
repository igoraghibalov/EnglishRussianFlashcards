package com.example.englishrussianflashcards.appscreens

import com.example.englishrussianflashcards.di.hilt.EspressoMainScreenCardsButtonUi
import com.example.englishrussianflashcards.di.hilt.EspressoMainScreenContinueButtonUi
import com.example.englishrussianflashcards.di.hilt.EspressoMainScreenHistoryButtonUi
import com.example.englishrussianflashcards.di.hilt.EspressoMainScreenNewCardButtonUi
import com.example.englishrussianflashcards.di.hilt.EspressoMainScreenQuitButtonUi
import com.example.englishrussianflashcards.screenuielements.ButtonUi
import javax.inject.Inject

/**
 * Created by Igor Aghibalov on 11.04.2026
 */
class MainMenuScreen @Inject constructor(
    @EspressoMainScreenContinueButtonUi private val continueButtonUi: ButtonUi,
    @EspressoMainScreenCardsButtonUi private val cardsButtonUi: ButtonUi,
    @EspressoMainScreenNewCardButtonUi private val newCardButtonUi: ButtonUi,
    @EspressoMainScreenHistoryButtonUi private val historyButtonUi: ButtonUi,
    @EspressoMainScreenQuitButtonUi private val quitButtonUi: ButtonUi) {

    fun clickContinueButton() { continueButtonUi.click() }
    fun clickCardsButton() { cardsButtonUi.click() }
    fun clickNewCardButton() { newCardButtonUi.click() }
    fun clickHistoryButton() { historyButtonUi.click() }
    fun clickQuitButton() { quitButtonUi.click() }
}