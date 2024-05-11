package com.example.englishrussianflashcards

import androidx.lifecycle.SavedStateHandle
import org.junit.Before

/**
 * Created by Igor Aghibalov on 10.05.2024
 */
abstract class ViewModelTester {
    protected lateinit var fakeCardRepository: CardRepository
    protected lateinit var viewModel: FlashcardsApplicationViewModel
    protected lateinit var fakeSavedStateHandle: SavedStateHandle

    @Before
    abstract fun setup()
}