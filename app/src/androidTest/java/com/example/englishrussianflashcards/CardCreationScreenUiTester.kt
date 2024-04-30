package com.example.englishrussianflashcards

import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.Test

/**
 * Created by Igor Aghibalov on 29.04.2024
 */
class CardCreationScreenUiTester: UiTester() {

    //TODO: refactor line 21,22 code duplication
    @Test
    fun testCardConfirmationDialogFragmentInflationOnCreateButtonClick() {
        val defaultTypingCharSequence = applicationContext.resources.getString(R.string.default_typing_char_sequence)
        val defaultWordGroupName = applicationContext.resources.getString(R.string.default_word_group_name)

        testFragmentInflation(R.id.new_button, R.id.word_typing_text_view)

        onView(withId(R.id.word_typing_text_view)).perform(typeText(defaultTypingCharSequence))

        onView(withId(R.id.translation_selection_text_view)).perform(click())
        onData(allOf(instanceOf(String::class.java))).atPosition(0).perform(click())

        onView(withId(R.id.english_example_selection_text_view)).perform(click())
        onData(allOf(instanceOf(String::class.java))).atPosition(0).perform(click())

        onView(withId(R.id.image_selection_text_view)).perform(click())
        onData(allOf(instanceOf(Drawable::class.java))).atPosition(0).perform(click())

        onView(withId(R.id.word_typing_text_view)).perform(typeText(defaultWordGroupName))

        testFragmentInflation(R.id.card_creation_button, R.id.flashcard_front_side_view)
    }


    @Test
    fun testCardCreationFragmentDataRetentionAfterScreenRotation() {
        val defaultTypingText = applicationContext.resources.getString(R.string.default_typing_char_sequence)
        onView(withId(R.id.word_typing_text_view)).perform(typeText(defaultTypingText))
        rotateScreen()
        onView(withId(R.id.word_typing_text_view)).check(matches(withText(defaultTypingText)))
    }


    @Test
    fun testCardCreationFragmentDataRetentionAfterProcessDeath() {
        testFragmentInflation(R.id.new_button, R.id.word_typing_text_view)
        mainActivityScenario.recreate()
        onView(withId(R.id.word_typing_text_view)).check(matches(isDisplayed()))
    }
}