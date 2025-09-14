package com.example.englishrussianflashcards

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Test

/**
 * Created by Igor Aghibalov on 12.09.2025
 */
@UninstallModules(DictionaryRepositoryModule::class, ImageRepositoryModule::class)
@HiltAndroidTest
class CardCreationImageRepositoryServerErrorUiTest: UiTest() {


    @Module
    @InstallIn(ViewModelComponent::class)
    class ServerErrorImageRepositoryTestModule {

        @Provides
        @ViewModelScoped
        fun provideServerErrorImageRepository(): ImageRepository {
            return ServerErrorImageRepository()
        }
    }



    @Module
    @InstallIn(ViewModelComponent::class)
    class DictionaryRepositorySuccessTestModule {

        @Provides
        @ViewModelScoped
        fun provideSuccessDictionaryRepository(): DictionaryRepository {
            return SuccessDictionaryRepository()
        }
    }


    override fun setUp() {
        hiltRule.inject()
    }


    @Test
    fun testImageSelectionWithImageApiServerError() {
        val cardCreationScreen = CardCreationScreen()
        val typingWordCharacters = "ap"

        with(cardCreationScreen) {
            typeWord(typingWordCharacters)
            selectWordFromDropdownMenu(wordPosition = 0)
            selectImage(imagePosition = 0)
            checkImageApiServerErrorDialogPresence()
            hideImageApiServerErrorDialog()
            checkImageSpinnerReplacementWithNoImageText()
        }
    }
}