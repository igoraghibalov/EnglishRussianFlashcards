package com.example.englishrussianflashcards

import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Test
import com.example.englishrussianflashcards.presentation.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule


/**
 * Created by Igor Aghibalov on 03.08.2025
 */
@UninstallModules(DictionaryRepositoryModule::class, ImageRepositoryModule::class)
@HiltAndroidTest
class CardCreationSuccessUiTest: UiTest() {


    @Module
    @InstallIn(ViewModelComponent::class)
    class DictionaryRepositorySuccessTestModule {

        @Provides
        @ViewModelScoped
        fun provideSuccessDictionaryRepository(): DictionaryRepository {
            return SuccessDictionaryRepository()
        }
    }


    @Module
    @InstallIn(ViewModelComponent::class)
    class ImageRepositorySuccessTestModule {

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
        val cardCreationScreen = CardCreationScreen()
        val typingWordCharacters = "ap"
        val typingCardGroupNameCharacters = "fr"
        val createdCard: Card
        val cardGroupScreen = CardGroupScreen()

        with(cardCreationScreen) {
            typeWord(typingWordCharacters)
            selectWordFromDropdownMenu(wordPosition = 0)
            selectTranslation(translationPosition = 0)
            selectExample(examplePosition = 0)
            selectImage(imagePosition = 0)
            typeCardGroupName(typingCardGroupNameCharacters)
            selectCardGroupNameFromDropdownMenu(cardGroupNamePosition = 0)

            createCard()

            createdCard = provideCreatedCard()

            cardGroupScreen.checkCardPresence(createdCard)
        }
    }
}