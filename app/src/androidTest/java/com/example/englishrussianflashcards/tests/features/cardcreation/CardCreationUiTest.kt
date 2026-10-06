package com.example.englishrussianflashcards.tests.features.cardcreation

import com.example.englishrussianflashcards.appscreens.CardCreationScreen
import com.example.englishrussianflashcards.appscreens.CardGroupScreen
import com.example.englishrussianflashcards.appscreens.MainMenuScreen
import com.example.englishrussianflashcards.tests.UiTest
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import javax.inject.Inject

/**
 * Created by Igor Aghibalov on 30.09.2026
 */
@UninstallModules(value = [RealDictionaryRepositoryModule::class,
                           RealImageRepositoryModule::class,
                           RealCardGroupTitleRepositoryModule::class])
@HiltAndroidTest
class CardCreationUiTest: UiTest() {

    @Inject
    lateinit var mainMenuScreen: MainMenuScreen

    @Inject
    lateinit var cardCreationScreen: CardCreationScreen

    @Inject
    lateinit var cardGroupScreen: CardGroupScreen


    @Before
    override fun setUp() {
        hiltRule.inject()
        mainMenuScreen.clickNewCardButton()
    }


    fun fillCard() {

        with (cardCreationScreen) {
            typeWordCharacters()
            selectWord()
            clearWord()
            typeWordCharacters()
            selectWord()
            selectTranslation()
            selectExample()
            selectImage()
            dropImage()
            selectImage()
            typeCardGroupName()
            selectCardGroupName()
        }
    }
}