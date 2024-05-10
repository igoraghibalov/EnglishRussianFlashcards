package com.example.englishrussianflashcards

import org.junit.Before

/**
 * Created by Igor Aghibalov on 10.05.2024
 */
abstract class ViewModelTester {
    protected lateinit var fakeCardRepository: CardRepository
    protected lateinit var viewModel: FlashcardsApplicationViewModel

    @Before
    abstract fun setup()
}