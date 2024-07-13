package com.example.englishrussianflashcards

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

/**
 * Created by Igor Aghibalov on 03.07.2024
 */


class CardWordExtractor(private val cardViewId: Int,
                        private val textOwnerChildViewId: Int): TextExtractor {


    override fun getText(): String {
        var cardWord = ""
        val cardViewInteraction = onView(withId(cardViewId))

        val cardWordExtractionAction = object: ViewAction {

            override fun getDescription(): String = "Card word extraction"

            override fun getConstraints(): Matcher<View> {
                return allOf(isAssignableFrom(CardView::class.java), isDisplayed())
            }

            override fun perform(uiController: UiController?, view: View?) {
                cardWord = view!!.findViewById<TextView>(textOwnerChildViewId).text.toString()
            }

        }
        cardViewInteraction.perform(cardWordExtractionAction)
        return cardWord
    }
}