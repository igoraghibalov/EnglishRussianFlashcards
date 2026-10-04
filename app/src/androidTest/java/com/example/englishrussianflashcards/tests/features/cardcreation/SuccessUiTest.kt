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
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import org.junit.Before
import javax.inject.Inject


/**
 * Created by Igor Aghibalov on 03.08.2025
 */
@UninstallModules(RealDictionaryRepositoryModule::class,
                  RealImageRepositoryModule::class,
                  RealCardGroupTitleRepositoryModule::class)
@HiltAndroidTest
class SuccessUiTest: UiTest() {

    @Inject
    lateinit var mainMenuScreen: MainMenuScreen

    @Inject
    lateinit var cardCreationScreen: CardCreationScreen

    @Inject
    lateinit var cardGroupScreen: CardGroupScreen


    @Module
    @InstallIn(SingletonComponent::class)
    abstract class FakeSuccessDictionaryRepositoryTestModule {

        @Binds
        abstract fun provideFakeSuccessDictionaryRepository(
                                fakeSuccessDictionaryRepository: FakeSuccessDictionaryRepository)
                : DictionaryRepository
    }


    @Module
    @InstallIn(SingletonComponent::class)
    abstract class FakeSuccessImageRepositoryTestModule {

        @Binds
        abstract fun provideFakeSuccessDictionaryRepository(
                                fakeSuccessImageRepository: FakeSuccessImageRepository)
                : ImageRepository
    }


    @Module
    @InstallIn(SingletonComponent::class)
    abstract class FakeSuccessCardGroupTitleRepositoryModule {

        @Binds
        abstract fun provideFakeSuccessCardGroupTitleRepository(
                                fakeSuccessCardGroupTitleRepository: FakeSuccessCardGroupTitleRepository)
                : CardGroupTitleRepository
    }
    

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