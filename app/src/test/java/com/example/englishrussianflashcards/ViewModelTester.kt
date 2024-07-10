package com.example.englishrussianflashcards

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

/**
 * Created by Igor Aghibalov on 10.05.2024
 */
abstract class ViewModelTester: UnitTester() {

    @get: Rule
    val ruleToUpdateLiveDataValueOnPureJVM = InstantTaskExecutorRule()
}