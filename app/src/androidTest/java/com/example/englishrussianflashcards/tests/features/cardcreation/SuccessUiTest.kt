package com.example.englishrussianflashcards.tests.features.cardcreation

import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Test


/**
 * Created by Igor Aghibalov on 03.08.2025
 */
@HiltAndroidTest
class SuccessUiTest: CardCreationUiTest() {


    @BindValue
    @JvmField
    val fakeSuccessDictionaryRepository = FakeSuccessDictionaryRepository()


    @BindValue
    @JvmField
    val fakeSuccessImageRepository = FakeSuccessImageRepository()


    @BindValue
    @JvmField
    val fakeSuccessCardRepository = FakeSuccessCardRepository()
    

    @Before
    override fun setUp() {
        hiltRule.inject()
        mainMenuScreen.clickNewCardButton()
    }


    @Test
    fun testSuccessfulCardCreation() {
        cardCreationScreen.hasDefaultTranscription()
        fillCard()
        cardCreationScreen.clickCreateCardButton()
        cardGroupScreen.checkCreatedCardPresence()
    }
}