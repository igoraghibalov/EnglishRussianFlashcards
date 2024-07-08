package com.example.englishrussianflashcards.casetesthandlers.inflation

import android.graphics.drawable.Drawable
import android.view.View
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.CaseUiTestHandler
import com.example.englishrussianflashcards.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf

/**
 * Created by Igor Aghibalov on 24.06.2024
 */
//TODO finish getSelectedViewDataMap() and handleCaseTest()
class CardDemoDialogFragmentInflationTestHandler(private val defaultTypingText: String): CaseUiTestHandler() {


    override fun handleCaseTest() {
        val viewDataTypeMap = mapOf(R.id.translation_selection_text_view to String::class.java,
                                    R.id.english_example_selection_text_view to String::class.java,
                                    R.id.transcription_text_view to String::class.java,
                                    R.id.image_selection_text_view to Drawable::class.java,
                                    R.id.group_selection_text_view to String::class.java)

        onView(withId(R.id.word_typing_text_view))
            .perform(typeText(defaultTypingText))

        //TODO: Fetch user selected data(translation, example, image, group) as a map

        clickOnView(R.id.card_creation_button)
        checkViewAppearance(R.id.card_front_side_view)

        //TODO: Assert user selected data map equality with CardDemoDialogFragment data
    }


    fun getSelectedViewDataMap(viewDataTypeMap: Map<Int, Class<*>>): Map<Int, Any> {
        val viewDataMap = mutableMapOf<Int, Any>()
        val viewData: Any

        for (viewId in viewDataTypeMap.keys) {
            clickOnView(viewId)

            onData(allOf(instanceOf(viewDataTypeMap[viewId])))
                .atPosition(0)
                .perform(click())
            viewData = getViewData(viewId)
            viewDataMap[viewId] = viewData
        }

        return viewDataMap
    }


    fun getViewData(viewId: Int): Any {
        val viewData: Any = 0

        val viewDataExtractionAction = object : ViewAction {


            override fun getDescription(): String {
                TODO("Not yet implemented")
            }


            override fun getConstraints(): Matcher<View> {
                TODO("Not yet implemented")
            }


            override fun perform(uiController: UiController?, view: View?) {
                TODO("Not yet implemented")
            }

        }

        onView(withId(viewId)).perform(viewDataExtractionAction)
        return viewData
    }
}