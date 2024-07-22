package com.example.englishrussianflashcards

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.englishrussianflashcards.domain.Card
import com.example.englishrussianflashcards.domain.LiveDataWrapper
import com.example.englishrussianflashcards.domain.Repository
import com.example.englishrussianflashcards.presentation.FlashcardsApplicationViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

/**
 * Created by Igor Aghibalov on 10.05.2024
 */
abstract class ViewModelTester: UnitTester() {
    protected lateinit var liveDataWrapper: LiveDataWrapper
    protected lateinit var repository: Repository
    protected lateinit var viewModel: FlashcardsAppViewModel
    protected lateinit var bundleWrapper: BundleWrapper
    protected lateinit var expectedData: UiState

    @get: Rule
    val ruleToUpdateLiveDataValueOnPureJVM = InstantTaskExecutorRule()

    abstract fun recreateViewModel()

    @Test
    fun testBundleDataRestore() {

        runBlocking {
            viewModel.fetchData()
            viewModel.saveData(bundleWrapper)

            recreateViewModel()
            viewModel.restoreData(bundleWrapper)

            Assert.assertTrue(DATA_RESTORE_TEST_FAILURE_MESSAGE, viewModel.containsData(expectedData))
        }
    }
}