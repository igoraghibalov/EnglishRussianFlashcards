package com.example.englishrussianflashcards

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

/**
 * Created by Igor Aghibalov on 27.06.2024
 */

//TODO: created during ViewAction usage practice: maybe useful in future, if not - remove
class GroupCardPresenceCheckAction(private val groupName: String,
                                   private val groupElementsCount: Int): ViewAction {

    override fun getDescription(): String =
        "Performing group card presence check action"


    override fun getConstraints(): Matcher<View> {
        return allOf(isAssignableFrom(RecyclerView::class.java),
                     isDisplayed())
    }


    override fun perform(uiController: UiController?, view: View?) {
        var isGroupCardPresent = false
        val recyclerView = view as RecyclerView
        val layoutManager = recyclerView.layoutManager
        val childCount = layoutManager!!.childCount
        var childView: CardView
        val noMatchingViewException = NoMatchingViewException.Builder().build()

        for (childIndex in 0 until childCount) {
            childView = layoutManager.getChildAt(childIndex)!! as CardView
            val groupNameTextView = childView.findViewById<TextView>(R.id.word_group_name_text_view)
            val cardsAmountTextView = childView.findViewById<TextView>(R.id.cards_amount_text_view)

            if (groupNameTextView.text == groupName
                        && cardsAmountTextView.text == groupElementsCount.toString()) {

                isGroupCardPresent = true
                break
            }
        }

        if(!isGroupCardPresent) {
            throw noMatchingViewException
        }
    }
}