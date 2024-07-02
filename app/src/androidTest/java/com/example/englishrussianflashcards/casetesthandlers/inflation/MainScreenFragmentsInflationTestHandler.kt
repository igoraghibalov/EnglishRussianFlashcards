package com.example.englishrussianflashcards.casetesthandlers.inflation

import androidx.test.espresso.Espresso.pressBack
import com.example.englishrussianflashcards.CaseUiTestHandler

/**
 * Created by Igor Aghibalov on 15.06.2024
 */
class MainScreenFragmentsInflationTestHandler(private val clickedViewToInflatedFragmentViewMap: Map<Int, Int>)
        : CaseUiTestHandler() {


    override fun handleCaseTest() {

        clickedViewToInflatedFragmentViewMap.forEach {
            clickOnView(it.key)
            checkViewAppearance(it.value)
            pressBack()
        }
    }
}