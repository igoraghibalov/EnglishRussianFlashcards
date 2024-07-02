package com.example.englishrussianflashcards.casetesthandlers.inflation

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.CaseUiTestHandler
import com.example.englishrussianflashcards.R
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf

/**
 * Created by Igor Aghibalov on 02.07.2024
 */
class CardFragmentInflationOnRecyclerViewItemClickTestHandler(private val recyclerViewId: Int)
    : CaseUiTestHandler() {


    override fun handleCaseTest() {
        onView(withId(recyclerViewId)).perform(click())
        onData(allOf(instanceOf(String::class.java))).atPosition(0).perform(click())
        checkViewAppearance(R.id.card_front_side_view)
    }
}