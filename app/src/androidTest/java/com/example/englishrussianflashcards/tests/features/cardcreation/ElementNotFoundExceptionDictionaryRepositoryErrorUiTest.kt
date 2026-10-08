package com.example.englishrussianflashcards.tests.features.cardcreation

import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest

/**
 * Created by Igor Aghibalov on 08.10.2026
 */
@HiltAndroidTest
class ElementNotFoundExceptionDictionaryRepositoryErrorUiTest
                                : BaseDictionaryRepositoryErrorUiTest() {

    @BindValue
    @JvmField
    val elementNotFoundExceptionDictionaryRepository = ElementNotFoundExceptionDictionaryRepository()

    override fun setUp() {
        super.setUp()
        dictionaryErrorHandlingLambda = { checkElementNotFoundExceptionDialogShow() }
    }
}