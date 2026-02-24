package com.example.englishrussianflashcards


import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher


private const val VIEW_DATA_EXTRACTION_EXCEPTION_MESSAGE = "Item has not been extracted by respective viewAction"
private const val VIEW_DATA_IS_NULL_EXCEPTION_MESSAGE = "Extracted view data is null"


/**
 * Created by Igor Aghibalov on 19.10.2025
 */
class EspressoViewDataExtraction<T: Any>(private val defaultDataValue: T,
                                         private val dataViewId: Int,
                                         private val dataViewMatcher: Matcher<View>,
                                         private val dataExtractionDescription: String,
                                         private val dataExtractionLambda: (view: View) -> T)
    : ViewDataExtraction<T> {

    override fun extractViewData(): T {
        var item: T = defaultDataValue

        val itemExtractionAction = object: ViewAction {

            override fun getDescription(): String? = dataExtractionDescription

            override fun getConstraints(): Matcher<View?>? {
                return dataViewMatcher as Matcher<View?>?
            }

            override fun perform(uiController: UiController?, view: View?) {

                if (view == null) throw RuntimeException(VIEW_DATA_IS_NULL_EXCEPTION_MESSAGE)

                item = dataExtractionLambda(view)
            }
        }

        Espresso.onView(ViewMatchers.withId(dataViewId)).perform(itemExtractionAction)

        if (item == defaultDataValue) throw RuntimeException(VIEW_DATA_EXTRACTION_EXCEPTION_MESSAGE)

        return item
    }
}