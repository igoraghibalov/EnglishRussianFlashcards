package com.example.englishrussianflashcards

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Test
import com.example.englishrussianflashcards.createcard.presentation.R
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf


/**
 * Created by Igor Aghibalov on 03.08.2025
 */

//TODO: finish remaining test methods
class CardCreationUiTest: UiTest() {
    

    override fun setUp() {
        clickViewById(com.example.englishrussianflashcards.R.id.create_card_button)
    }


    @Test
    fun selectWordFromDropDownMenu() {
        val wordAutoCompleteTextViewId = R.id.word_auto_complete_text_view
        val dropDownMenuRootMatcher = RootMatchers.isPlatformPopup()
        val dataMatcher = instanceOf<String>(String::class.java)
        val typingCharacters = "ab"
        var wordToSelect: String

        typeCharacters(wordAutoCompleteTextViewId, typingCharacters)
        wordToSelect = extractTextFromDropdownMenuItem(itemPosition = 0,
                                                       dropDownMenuRootMatcher,
                                                       dataMatcher)
        clearText(wordAutoCompleteTextViewId)

        typeCharacters(wordAutoCompleteTextViewId, typingCharacters)
        clickViewByDataWithinRoot(dropDownMenuRootMatcher, dataMatcher, dataPosition = 0)

        testViewPresence(allOf(withId(wordAutoCompleteTextViewId), withText(wordToSelect)))
    }
}