package com.example.englishrussianflashcards.tests

import com.example.englishrussianflashcards.Card
import com.example.englishrussianflashcards.tests.UiTest
import com.example.englishrussianflashcards.appscreens.CardCreationScreen
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
class CardCreationDictionaryRepositoryErrorUiTest: UiTest() {


    @Module
    @InstallIn(ViewModelComponent::class)
    class ErrorDictionaryRepositoryTestModule {

        @Provides
        @ViewModelScoped
        fun provideErrorDictionaryRepository(): DictionaryRepository {
            return ErrorDictionaryRepository()
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


    override fun setUp() {
        hiltRule.inject()
    }


    @Test
    fun testCardCreationWithDictionaryDatabaseError() {}
}