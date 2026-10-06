package com.example.englishrussianflashcards.tests.features.cardcreation

import com.example.englishrussianflashcards.appscreens.CardCreationScreen
import com.example.englishrussianflashcards.appscreens.CardGroupScreen
import com.example.englishrussianflashcards.appscreens.MainMenuScreen
import com.example.englishrussianflashcards.tests.UiTest
import dagger.Binds
import org.junit.Test
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import org.junit.Before
import javax.inject.Inject


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
    val fakeSuccessCardGroupTitleRepository = FakeSuccessCardGroupTitleRepository()
    

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