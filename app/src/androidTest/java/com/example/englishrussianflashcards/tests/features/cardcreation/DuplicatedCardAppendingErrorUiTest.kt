package com.example.englishrussianflashcards.tests.features.cardcreation

import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

private const val DUPLICATED_CARD_SAVING_ERROR_DIALOG_MESSAGE = "Card created already"

/**
 * Created by Igor Aghibalov on 30.09.2026
 */

@HiltAndroidTest
class DuplicatedCardAppendingErrorUiTest: BaseCardRepositoryError() {


    @BindValue
    @JvmField
    val duplicateAppendingErrorCardRepository = DuplicateAppendingErrorCardRepository()


    override fun setUp() {
        super.setUp()
        errorDialogMessage = DUPLICATED_CARD_SAVING_ERROR_DIALOG_MESSAGE
    }
}