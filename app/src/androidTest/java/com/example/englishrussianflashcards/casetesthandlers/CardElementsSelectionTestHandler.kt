package com.example.englishrussianflashcards.casetesthandlers

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.englishrussianflashcards.CaseUiTestHandler
import com.example.englishrussianflashcards.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.not
import org.hamcrest.TypeSafeMatcher

/**
 * Created by Igor Aghibalov on 23.06.2024
 */
class CardElementsSelectionTestHandler(private val applicationContext: Context): CaseUiTestHandler() {


    override fun handleCaseTest() {
        val defaultTypingCharSequence = applicationContext.resources.getString(R.string.default_typing_char_sequence)
        val defaultTextMatcher = not(withText(""))
        val stringClassObject = String::class.java

        clickOnView(R.id.new_button)
        checkViewAppearance(R.id.word_typing_text_view)

        onView(ViewMatchers.withId(R.id.word_typing_text_view))
            .perform(ViewActions.typeText(defaultTypingCharSequence))

        checkUserSelectedItemPresence(R.id.translation_selection_text_view, stringClassObject, defaultTextMatcher)
        checkUserSelectedItemPresence(R.id.english_example_selection_text_view, stringClassObject, defaultTextMatcher)
        checkUserSelectedItemPresence(R.id.image_selection_text_view, Drawable::class.java, SelectedDrawableMatcher(R.drawable.default_word_image))
        checkUserSelectedItemPresence(R.id.group_selection_text_view, stringClassObject, defaultTextMatcher)
    }


    inline fun <reified T> checkUserSelectedItemPresence(viewId: Int,
                                                         itemClassObject: Class<T>,
                                                         itemMatcher: Matcher<View>) {
        clickOnView(viewId)
        onData(Matchers.allOf(Matchers.instanceOf(itemClassObject)))
            .atPosition(0)
            .perform(ViewActions.click())
        onView(ViewMatchers.withId(viewId)).check(matches(itemMatcher))
    }


    override fun doAfterCaseTestHandling() {}
}


class SelectedDrawableMatcher(private val defaultDrawableId: Int): TypeSafeMatcher<View>() {

    override fun describeTo(description: Description?) {
        description!!.appendText("ImageView contains user selected drawable")
    }


    override fun matchesSafely(item: View?): Boolean {
        val defaultDrawable = item!!.context.getDrawable(defaultDrawableId)
        return (item as ImageView).drawable != defaultDrawable
    }

}