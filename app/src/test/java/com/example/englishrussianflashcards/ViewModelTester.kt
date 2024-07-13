package com.example.englishrussianflashcards

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.englishrussianflashcards.domain.Repository
import org.junit.Rule

/**
 * Created by Igor Aghibalov on 10.05.2024
 */
abstract class ViewModelTester: UnitTester() {
    protected lateinit var liveDataWrapper: LiveDataWrapper
    protected lateinit var repository: Repository
    protected lateinit var viewModel: FlashcardsAppViewModel

    @get: Rule
    val ruleToUpdateLiveDataValueOnPureJVM = InstantTaskExecutorRule()
}