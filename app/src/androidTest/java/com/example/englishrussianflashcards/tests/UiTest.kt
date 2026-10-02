package com.example.englishrussianflashcards.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.englishrussianflashcards.presentation.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * Created by Igor Aghibalov on 30.04.2025
 */
@RunWith(AndroidJUnit4::class)
abstract class UiTest {

    @get: Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get: Rule(order = 1)
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    abstract fun setUp()
}