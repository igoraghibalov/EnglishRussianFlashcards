package com.example.englishrussianflashcards.tests.features.cardcreation


import com.example.englishrussianflashcards.appscreens.CardCreationScreen
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

/**
 * Created by Igor Aghibalov on 04.10.2026
 */
@HiltAndroidTest
open class BaseDictionaryRepositoryErrorUiTest: CardCreationUiTest() {

    protected var dictionaryErrorHandlingLambda: CardCreationScreen.() -> Unit = {}


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
            dictionaryErrorHandlingLambda.invoke(cardCreationScreen)
        }
    }
}