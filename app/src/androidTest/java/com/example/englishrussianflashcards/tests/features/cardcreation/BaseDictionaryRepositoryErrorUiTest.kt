package com.example.englishrussianflashcards.tests.features.cardcreation


import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

/**
 * Created by Igor Aghibalov on 04.10.2026
 */
@HiltAndroidTest
open class BaseDictionaryRepositoryErrorUiTest: CardCreationUiTest() {

    protected var dictionaryErrorDialogMessage: String = ""


    @BindValue
    @JvmField
    val fakeSuccessImageRepository = FakeSuccessImageRepository()


    @BindValue
    @JvmField
    val fakeSuccessCardGroupTitleRepository = FakeSuccessCardGroupTitleRepository()


    @Test
    fun testDictionaryRepositoryErrorDialogShow() {

        try {
            fillCard()
        } catch (e: Exception) {
            cardCreationScreen.checkErrorDialogPresence(dictionaryErrorDialogMessage)
        }
    }
}