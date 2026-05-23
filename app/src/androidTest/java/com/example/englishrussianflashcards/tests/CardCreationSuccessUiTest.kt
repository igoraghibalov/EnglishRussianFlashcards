package com.example.englishrussianflashcards.tests

import com.example.englishrussianflashcards.UiTest
import com.example.englishrussianflashcards.appscreens.CardCreationScreen
import com.example.englishrussianflashcards.appscreens.CardGroupScreen
import com.example.englishrussianflashcards.appscreens.MainMenuScreen
import org.junit.Test
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import javax.inject.Inject


/**
 * Created by Igor Aghibalov on 03.08.2025
 */
@UninstallModules(DictionaryRepositoryModule::class, ImageRepositoryModule::class)
@HiltAndroidTest
class CardCreationSuccessUiTest: UiTest() {

    @Inject
    lateinit var mainMenuScreen: MainMenuScreen

    @Inject
    lateinit var cardCreationScreen: CardCreationScreen

    @Inject
    lateinit var cardGroupScreen: CardGroupScreen


    @Module
    @InstallIn(ViewModelComponent::class)
    class SuccessDictionaryRepositoryTestModule {

        @Provides
        @ViewModelScoped
        fun provideSuccessDictionaryRepository(): DictionaryRepository {
            return SuccessDictionaryRepository()
        }
    }


    @Module
    @InstallIn(ViewModelComponent::class)
    class SuccessImageRepositoryTestModule {

        @Provides
        @ViewModelScoped
        fun provideSuccessImageRepository(): ImageRepository {
            return SuccessImageRepository()
        }
    }
    

    @Before
    override fun setUp() {
        hiltRule.inject()
        mainMenuScreen.clickNewCardButton()
    }


    @Test
    fun testSuccessfulCardCreation() {
        fillCard(cardElementsSelectionPosition = 0)

        with (cardCreationScreen) {
            clickCreateCardButton()
            cardGroupScreen.checkCreatedCardPresence()
        }
    }


    @Test
    fun testMessageShowOnSameCardCreationAttempt() {
        fillCard(cardElementsSelectionPosition = 0)
        cardGroupScreen.clickAddCardButton()
        fillCard(0)
        cardGroupScreen.checkCardDuplication()
    }


    fun fillCard(cardElementsSelectionPosition: Int) {

        with (cardCreationScreen) {
            typeWordCharacters()
            selectWord(cardElementsSelectionPosition)
            clearWord()
            typeWordCharacters()
            selectWord(cardElementsSelectionPosition)
            selectTranslation(cardElementsSelectionPosition)
            selectExample(cardElementsSelectionPosition)
            selectImage(cardElementsSelectionPosition)
            dropImage()
            selectImage(cardElementsSelectionPosition)
            typeCardGroupName()
            selectCardGroupName(cardElementsSelectionPosition)
        }
    }
}