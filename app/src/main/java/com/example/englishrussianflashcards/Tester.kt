package com.example.englishrussianflashcards

/**
 * Created by Igor Aghibalov on 20.06.2024
 */
abstract class Tester: TestEnvironmentSetuper, CaseTester {


    override fun testCase(caseTestHandler: CaseTestHandler) {
        caseTestHandler.handleCaseTest()
    }
}