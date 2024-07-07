package com.example.englishrussianflashcards.testers

import com.example.englishrussianflashcards.CaseTestHandler
import com.example.englishrussianflashcards.CaseTester
import com.example.englishrussianflashcards.TestEnvironmentSetuper

/**
 * Created by Igor Aghibalov on 20.06.2024
 */
abstract class Tester: TestEnvironmentSetuper, CaseTester {


    override fun testCase(caseTestHandler: CaseTestHandler) {
        caseTestHandler.handleCaseTest()
    }
}