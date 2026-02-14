package com.example.englishrussianflashcards.screenuielements.espresso

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.ListItemSelectionTask
import com.example.englishrussianflashcards.ViewDataExtraction
import com.example.englishrussianflashcards.screenuielements.AutoCompleteListItemSelectionUi
import com.google.android.material.internal.CheckableImageButton
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf

/**
* Created by Igor Aghibalov on 07.02.2026
*/
class EspressoAutoCompleteListItemSelectionUi(private val listItemSelectionTask: ListItemSelectionTask,
                                              private val typingCharacters: String,
                                              private val listShowTriggerId: Int,
                                              private val wordExtraction: ViewDataExtraction<String>)
    : AutoCompleteListItemSelectionUi {


    override fun typeCharacters() {
        onView(withId(listShowTriggerId)).perform(typeText(typingCharacters))
    }

    override fun clearText() {
        onView(allOf(instanceOf(CheckableImageButton::class.java),
                     isDisplayed(),
                     ClearButtonOwnerMatcher(listShowTriggerId)))
            .perform(click())
    }

    override fun showList() {
        typeCharacters()
    }

    override fun selectItem() {
        listItemSelectionTask.run()
    }

    override fun extractViewData(): String = wordExtraction.extractViewData()
}
