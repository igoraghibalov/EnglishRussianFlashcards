package com.example.englishrussianflashcards

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import org.junit.Before
import org.junit.Rule

/**
 * Created by Igor Aghibalov on 10.05.2024
 */
abstract class ViewModelTester {

    @get: Rule
    val ruleToUpdateLiveDataValue = InstantTaskExecutorRule()

    protected lateinit var fakeSavedStateHandle: SavedStateHandle

    @Before
    abstract fun setup()
}