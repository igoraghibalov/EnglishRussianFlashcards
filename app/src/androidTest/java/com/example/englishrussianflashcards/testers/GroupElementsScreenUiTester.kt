package com.example.englishrussianflashcards.testers

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.R
import com.example.englishrussianflashcards.casetesthandlers.inflation.RecyclerViewDisplayTestHandler
import com.example.englishrussianflashcards.casetesthandlers.rotation.ScrollPositionRetentionOnScreenRotationTestHandler
import com.example.englishrussianflashcards.presentation.GroupCardViewHolder
import org.junit.Test

/**
 * Created by Igor Aghibalov on 05.07.2024
 */
/*
 * Test cases:
 * 1. recycler view item scroll position on screen rotation
 * 2. assert: groupElementRecyclerView has displayed on a groupCard click
 * 3. Every card groupTitle of recycler view == groupCard.groupTitle
 *
 *
 */
class GroupElementsScreenUiTester: UiTester() {

    override fun setupTestEnvironment() {
        super.setupTestEnvironment()

        val groupCardRecyclerViewId = R.id.group_card_recycler_view
        val groupCardRecyclerViewItemClickAction = actionOnItemAtPosition<GroupCardViewHolder>(0, click())

        onView(withId(R.id.cards_button)).perform(click())
        onView(withId(groupCardRecyclerViewId)).check(matches(isDisplayed()))

        onView(withId(groupCardRecyclerViewId)).perform(groupCardRecyclerViewItemClickAction)
    }

    @Test
    fun testGroupElementRecyclerViewDisplayOnGroupCardClick() {
        testCase(caseTestHandler = RecyclerViewDisplayTestHandler(R.id.group_elements_recycler_view))
    }


    @Test
    fun testItemScrollPositionRetentionAfterScreenRotation() {
        testCase(caseTestHandler
            = ScrollPositionRetentionOnScreenRotationTestHandler<GroupElementViewHolder>(R.id.group_elements_recycler_view,
                                                                                         R.id.group_element_card_view,
                                                                                         applicationContext))
    }

}