package com.example.englishrussianflashcards.appscreens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.englishrussianflashcards.appscreens.screenuielements.AutoCompleteDropdownMenuUi
import com.example.englishrussianflashcards.appscreens.screenuielements.ClickableUi
import com.example.englishrussianflashcards.appscreens.screenuielements.DropdownMenuUi
import com.example.englishrussianflashcards.appscreens.screenuielements.ImageUi
import com.example.englishrussianflashcards.appscreens.screenuielements.TextUi
import com.example.englishrussianflashcards.di.hilt.EspressoCardCreationButtonUi
import com.example.englishrussianflashcards.di.hilt.EspressoCardGroupTitleUi
import com.example.englishrussianflashcards.di.hilt.EspressoExampleUi
import com.example.englishrussianflashcards.di.hilt.EspressoImageUi
import com.example.englishrussianflashcards.di.hilt.EspressoTranscriptionUi
import com.example.englishrussianflashcards.di.hilt.EspressoTranslationUi
import com.example.englishrussianflashcards.di.hilt.EspressoWordUi
import javax.inject.Inject


/**
 * Created by Igor Aghibalov on 14.09.2025
 */

/* TODO: Create test_card_data.json to use
 */
class CardCreationScreen @Inject constructor(
    @EspressoWordUi private val wordUi: AutoCompleteDropdownMenuUi,
    @EspressoTranslationUi private val translationUi: DropdownMenuUi,
    @EspressoExampleUi private val exampleUi: DropdownMenuUi,
    @EspressoTranscriptionUi private val transcriptionUi: TextUi,
    @EspressoImageUi private val imageUi: ImageUi,
    @EspressoCardGroupTitleUi private val cardGroupTitleUi: AutoCompleteDropdownMenuUi,
    @EspressoCardCreationButtonUi private val cardCreationButtonUi: ClickableUi) {


    fun typeWordCharacters() {
        wordUi.typeCharacters()
    }

    fun clearWord() {
        wordUi.clearText()
    }
    
    fun selectWord() {
        wordUi.selectItem()
    }

    fun hasDefaultTranscription() {
        transcriptionUi.hasText()
    }


    fun selectTranslation() {
        translationUi.showMenu()
        translationUi.selectItem()
    }


    fun selectExample() {
        exampleUi.showMenu()
        exampleUi.selectItem()
    }


    fun selectImage() {
        imageUi.showMenu()
        imageUi.selectItem()
    }


    fun dropImage() {
        imageUi.dropSelection()
    }


    fun typeCardGroupName() {
        cardGroupTitleUi.typeCharacters()
    }


    fun selectCardGroupName() {
        cardGroupTitleUi.selectItem()
    }


    fun clickCreateCardButton() {
        cardCreationButtonUi.click()
    }


    fun checkErrorDialogPresence(errorDialogMessage: String) {
        onView(withText(errorDialogMessage)).check(matches(isDisplayed()))
    }
}