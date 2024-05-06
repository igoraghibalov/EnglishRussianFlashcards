package com.example.englishrussianflashcards

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Test

/**
 * Created by Igor Aghibalov on 05.05.2024
 */
class CardScreenUiTester: UiTester() {


    override fun setup() {
        super.setup()
        testFragmentInflation(R.id.continue_button, R.id.card_front_side_view)
    }

    
    @Test
    fun testNewCardRevealingOnSwipeLeftAndRight() {
        val initialCardWord: String = getCardWord()
        val cardViewInteraction = onView(withId(R.id.card_front_side_view))
        cardViewInteraction.perform(swipeLeft())
        onView(withText(initialCardWord)).check(matches(not(isDisplayed())))
        cardViewInteraction.perform(swipeRight())
        onView(withText(initialCardWord)).check(matches(isDisplayed()))
    }

    @Test
    fun testCardRotationOnClick() {
        val cardViewInteraction = onView(withId(R.id.card_front_side_view))
        cardViewInteraction.perform(click())
        onView(withId(R.id.word_image_view)).check(matches(isDisplayed()))
    }

    @Test
    fun testCardRetentionOnScreenRotation() {
        val initialCardWord: String = getCardWord()
        rotateScreen()
        onView(withText(initialCardWord)).check(matches(isDisplayed()))
    }


    fun getCardWord(): String {
        var cardWord: String = ""
        val cardViewInteraction = onView(withId(R.id.card_front_side_view))
        val cardWordExtractionAction = object: ViewAction {

            override fun getDescription(): String = "Card word extraction"

            override fun getConstraints(): Matcher<View> {
                return allOf(isAssignableFrom(CardView::class.java), isDisplayed())
            }

            override fun perform(uiController: UiController?, view: View?) {
                cardWord = view!!.findViewById<TextView>(R.id.word_text_view).text.toString()
            }

        }
        cardViewInteraction.perform(cardWordExtractionAction)
        return cardWord
    }
}