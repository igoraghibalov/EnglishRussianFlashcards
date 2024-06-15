package com.example.englishrussianflashcards

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers

/**
 * Created by Igor Aghibalov on 15.06.2024
 */

class MainScreenFragmentsInflationTestHandler(private val clickedViewToInflatedFragmentViewMap: Map<Int, Int>)
    : FragmentInflationTestHandler, ViewClickHandler, ViewAppearanceTester {

    override fun handleTest() {

        clickedViewToInflatedFragmentViewMap.forEach {
            clickOnView(it.key)
            checkViewAppearance(it.value)
            doAfterFragmentInflationTest()
        }
    }


    override fun doAfterFragmentInflationTest() {
        Espresso.pressBack()
    }

    override fun clickOnView(viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId))
            .perform(ViewActions.click())
    }

    override fun checkViewAppearance(viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}