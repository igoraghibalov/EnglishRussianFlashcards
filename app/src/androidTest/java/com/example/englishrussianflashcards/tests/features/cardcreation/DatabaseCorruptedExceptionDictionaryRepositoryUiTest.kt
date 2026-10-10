package com.example.englishrussianflashcards.tests.features.cardcreation

import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest


private const val DATABASE_CORRUPTED_DIALOG_MESSAGE = "Dictionary database reading error"


/**
 * Created by Igor Aghibalov on 09.10.2026
 */
@HiltAndroidTest
class DatabaseCorruptedExceptionDictionaryRepositoryUiTest
                         : BaseDictionaryRepositoryErrorUiTest() {


    @BindValue
    @JvmField
    val databaseCorruptedExceptionDictionaryRepository = DatabaseCorruptedExceptionDictionaryRepository()


    override fun setUp() {
        super.setUp()
        dictionaryErrorDialogMessage = DATABASE_CORRUPTED_DIALOG_MESSAGE
    }
}