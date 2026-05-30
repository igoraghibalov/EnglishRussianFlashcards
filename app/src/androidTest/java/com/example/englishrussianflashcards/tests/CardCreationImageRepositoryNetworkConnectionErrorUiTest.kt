package com.example.englishrussianflashcards.tests

import com.example.englishrussianflashcards.UiTest
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
class CardCreationImageRepositoryNetworkConnectionErrorUiTest: UiTest() {


    @Module
    @InstallIn(ViewModelComponent::class)
    class NetworkConnectionErrorImageRepositoryTestModule {

        @Provides
        @ViewModelScoped
        fun provideNetworkConnectionErrorImageRepository(): ImageRepository {
            return NetworkConnectionErrorImageRepository()
        }
    }



    @Module
    @InstallIn(ViewModelComponent::class)
    class SuccessDictionaryRepositoryTestModule {

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
    fun testImageSelectionOnNetworkConnectionFix() {}


    @Test
    fun testImageSelectionOnNetworkConnectionError() {}
}