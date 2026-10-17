package com.example.englishrussianflashcards.tests.features.cardcreation

import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest


private const val NEW_CARD_SAVING_ERROR_DIALOG_MESSAGE = "Card database error. New card saving is failed"

/**
 * Created by Igor Aghibalov on 17.10.2026
 */
@HiltAndroidTest
class NewCardSavingErrorUiTest: BaseCardRepositoryError() {


    @BindValue
    @JvmField
    val savingErrorCardRepository = SavingErrorCardRepository()


    override fun setUp() {
        super.setUp()
        errorDialogMessage = NEW_CARD_SAVING_ERROR_DIALOG_MESSAGE
    }
}