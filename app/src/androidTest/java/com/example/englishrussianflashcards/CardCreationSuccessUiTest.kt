package com.example.englishrussianflashcards

import com.example.englishrussianflashcards.appscreens.CardCreationScreen
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
    }


    @Test
    fun testSuccessfulCardCreation() {
        val expectedCard: Card

        with<CardCreationScreen, Unit>(cardCreationScreen) {
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
            expectedCard = provideExpectedCard()
            createCard()

            cardGroupScreen.checkCardPresence(expectedCard)
        }
    }
}