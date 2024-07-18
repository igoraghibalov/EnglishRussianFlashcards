package com.example.englishrussianflashcards

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Created by Igor Aghibalov on 18.07.2024
 */
class MainDispatcherRule(private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher())
    : TestWatcher() {


    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }


    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}