package com.example.englishrussianflashcards.tests.features.cardcreation

import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest


private const val ELEMENT_NOT_FOUND_DIALOG_MESSAGE = "No elements found"


/**
 * Created by Igor Aghibalov on 08.10.2026
 */
@HiltAndroidTest
class ElementNotFoundExceptionDictionaryRepositoryUiTest
                                : BaseDictionaryRepositoryErrorUiTest() {

    @BindValue
    @JvmField
    val elementNotFoundExceptionDictionaryRepository = ElementNotFoundExceptionDictionaryRepository()

    override fun setUp() {
        super.setUp()
        dictionaryErrorDialogMessage = ELEMENT_NOT_FOUND_DIALOG_MESSAGE
    }
}