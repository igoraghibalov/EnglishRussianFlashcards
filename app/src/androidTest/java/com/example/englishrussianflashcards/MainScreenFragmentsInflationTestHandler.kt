package com.example.englishrussianflashcards

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.Test

/**
 * Created by Igor Aghibalov on 15.06.2024
 */
class MainScreenFragmentsInflationTestHandler(private val clickedViewToInflatedFragmentViewMap: Map<Int, Int>)
        : CaseTestHandler() {


    override fun handleCaseTest() {

        clickedViewToInflatedFragmentViewMap.forEach {
            clickOnView(it.key)
            checkViewAppearance(it.value)
        }
    }


    override fun doAfterCaseTestHandling() {
        Espresso.pressBack()
    }
}