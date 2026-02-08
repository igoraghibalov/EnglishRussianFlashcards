package com.example.englishrussianflashcards.appscreens

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.example.englishrussianflashcards.CELLULAR_DATA_DISABLING_ADB_SHELL_COMMAND
import com.example.englishrussianflashcards.CELLULAR_DATA_ENABLING_ADB_SHELL_COMMAND
import com.example.englishrussianflashcards.Card
import com.example.englishrussianflashcards.WI_FI_DISABLING_ADB_SHELL_COMMAND
import com.example.englishrussianflashcards.WI_FI_ENABLING_ADB_SHELL_COMMAND
import com.example.englishrussianflashcards.screenuielements.AutoCompleteListItemSelectionUi
import com.example.englishrussianflashcards.screenuielements.ButtonUi
import com.example.englishrussianflashcards.screenuielements.DialogUi
import com.example.englishrussianflashcards.screenuielements.DroppableListItemSelectionUi
import com.example.englishrussianflashcards.screenuielements.ListItemSelectionUi
import com.example.englishrussianflashcards.screenuielements.TextUi
import javax.inject.Inject


/**
 * Created by Igor Aghibalov on 14.09.2025
 */
class CardCreationScreen @Inject constructor(
    @EspressoWordSelectionUi private val wordSelectionUi: AutoCompleteListItemSelectionUi,
    @EspressoTranslationSelectionUi private val translationSelectionUi: ListItemSelectionUi,
    @EspressoExampleSelectionUi private val exampleSelectionUi: ListItemSelectionUi,
    @EspressoTextViewTranscriptionUi private val transcriptionUi: TextUi<String>,
    @EspressoImageSelectionUi private val imageSelectionUi: DroppableListItemSelectionUi,
    @EspressoCardGroupTitleSelectionUi private val cardGroupTitleSelectionUi: AutoCompleteListItemSelectionUi,
    @EspressoCardCreationButtonUi private val cardCreationButtonUi: ButtonUi,
    @EspressoImageApiServerErrorDialogUi private val imageApiServerErrorDialogUi: DialogUi,
    @EspressoNetworkConnectionErrorDialogUi private val networkConnectionErrorDialogUi: DialogUi) {


    fun typeWordCharacters() {
        wordSelectionUi.typeCharacters()
    }

    fun clearWord() {
        wordSelectionUi.clearText()
    }
    
    fun selectWord() {
        wordSelectionUi.selectItem()
    }


    fun selectTranslation() {
        translationSelectionUi.showList()
        translationSelectionUi.selectItem()
    }


    fun selectExample() {
        exampleSelectionUi.showList()
        exampleSelectionUi.selectItem()
    }


    fun selectImage() {
        imageSelectionUi.showList()
        imageSelectionUi.selectItem()
    }


    fun dropImage() {
        imageSelectionUi.dropSelection()
    }


    fun typeCardGroupName() {
        cardGroupTitleSelectionUi.typeCharacters()
    }


    fun selectCardGroupName() {
        cardGroupTitleSelectionUi.selectItem()
    }


    fun createCard() {
        cardCreationButtonUi.click()
    }


    fun checkImageApiServerErrorDialogPresence() {
        imageApiServerErrorDialogUi.isDisplayed()
    }


    fun hideImageApiServerErrorDialog() {
        imageApiServerErrorDialogUi.hide()
    }


    fun checkNetworkConnectionErrorDialogPresence() {
        networkConnectionErrorDialogUi.isDisplayed()
    }


    fun hideNetworkConnectionErrorDialog() {
        networkConnectionErrorDialogUi.hide()
    }


    fun enableNetworkConnection() {
        val uiDevice = provideUiDevice()
        uiDevice.executeShellCommand(WI_FI_ENABLING_ADB_SHELL_COMMAND)
        uiDevice.executeShellCommand(CELLULAR_DATA_ENABLING_ADB_SHELL_COMMAND)
    }


    fun disableNetworkConnection() {
        val uiDevice = provideUiDevice()
        uiDevice.executeShellCommand(WI_FI_DISABLING_ADB_SHELL_COMMAND)
        uiDevice.executeShellCommand(CELLULAR_DATA_DISABLING_ADB_SHELL_COMMAND)
    }


    fun provideUiDevice() = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())


    fun provideExpectedCard(): Card {
        return Card(word = wordSelectionUi.extractViewData(),
                    translation = translationSelectionUi.extractViewData(),
                    transcription = transcriptionUi.extractViewData(),
                    example = exampleSelectionUi.extractViewData(),
                    imageTag = imageSelectionUi.extractViewData(),
                    cardGroupName = cardGroupTitleSelectionUi.extractViewData())
    }
}