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
    protected var liveDataWrapper: LiveDataWrapper<*> = LiveDataWrapper.DefaultLiveDataWrapper()
    protected var repository: Repository = DefaultRepository()
    protected var viewModel: FlashcardsAppViewModel = DefaultViewModel()

    @get: Rule
    val ruleToUpdateLiveDataValueOnPureJVM = InstantTaskExecutorRule()
}