package com.example.englishrussianflashcards

import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Igor Aghibalov on 10.07.2024
 */
@RunWith(JUnit4::class)
abstract class UnitTester: Tester() {
    protected val NO_INTERNET_CONNECTION_TEST_FAILURE_MESSAGE = "Internet connection failure wrong handling"
    protected val SUCCESSFUL_DATA_EXTRACTION_TEST_FAILURE_MESSAGE = "Internet connection failure wrong handling"
    protected val NO_INTERNET_CONNECTION_TEST_WRONG_RESPONSE_STATUS_MESSAGE = "Internet connection failure wrong handling"
    protected val NO_INTERNET_CONNECTION_RESPONSE_STATUS = 503
    @JvmField
    @Rule
    val mainDispatcherRule = MainDispatcherRule()
}