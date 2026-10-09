package com.example.englishrussianflashcards.tests.features.cardcreation

import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest

/**
 * Created by Igor Aghibalov on 09.10.2026
 */
@HiltAndroidTest
class DiskIOExceptionDictionaryRepositoryUiTest: BaseDictionaryRepositoryErrorUiTest() {


    @BindValue
    @JvmField
    val diskIOExceptionDictionaryRepository = DiskIOExceptionDictionaryRepository()

    override fun setUp() {
        super.setUp()
        dictionaryErrorHandlingLambda = { checkDiskIOExceptionDialogShow() }
    }
}