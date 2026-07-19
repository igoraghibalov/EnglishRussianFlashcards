package com.example.englishrussianflashcards.appscreens

import com.example.englishrussianflashcards.appscreens.screenuielements.ClickableUi
import com.example.englishrussianflashcards.di.hilt.EspressoMainScreenCardsButtonUi
import com.example.englishrussianflashcards.di.hilt.EspressoMainScreenContinueButtonUi
import com.example.englishrussianflashcards.di.hilt.EspressoMainScreenHistoryButtonUi
import com.example.englishrussianflashcards.di.hilt.EspressoMainScreenNewCardButtonUi
import com.example.englishrussianflashcards.di.hilt.EspressoMainScreenQuitButtonUi
import javax.inject.Inject

/**
 * Created by Igor Aghibalov on 11.04.2026
 */
class MainMenuScreen @Inject constructor(
    @EspressoMainScreenContinueButtonUi private val continueButtonUi: ClickableUi,
    @EspressoMainScreenCardsButtonUi private val cardsButtonUi: ClickableUi,
    @EspressoMainScreenNewCardButtonUi private val newCardButtonUi: ClickableUi,
    @EspressoMainScreenHistoryButtonUi private val historyButtonUi: ClickableUi,
    @EspressoMainScreenQuitButtonUi private val quitButtonUi: ClickableUi) {

    fun clickContinueButton() { continueButtonUi.click() }
    fun clickCardsButton() { cardsButtonUi.click() }
    fun clickNewCardButton() { newCardButtonUi.click() }
    fun clickHistoryButton() { historyButtonUi.click() }
    fun clickQuitButton() { quitButtonUi.click() }
}