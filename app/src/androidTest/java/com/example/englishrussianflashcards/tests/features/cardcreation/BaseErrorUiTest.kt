package com.example.englishrussianflashcards.tests.features.cardcreation

import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

/**
 * Created by Igor Aghibalov on 10.10.2026
 */
@HiltAndroidTest
open class BaseErrorUiTest: CardCreationUiTest() {

    protected var errorDialogMessage: String = ""


    @Test
    fun testDictionaryRepositoryErrorDialogShow() {

        try {
            fillCard()
        } catch (e: Exception) {
            cardCreationScreen.checkErrorDialogPresence(errorDialogMessage)
        }
    }
}