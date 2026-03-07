package com.example.englishrussianflashcards.di.hilt

import android.widget.TextView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.englishrussianflashcards.ClearButtonOwnerMatcher
import com.example.englishrussianflashcards.DEFAULT_STRING
import com.example.englishrussianflashcards.EspressoListItemSelectionTask
import com.example.englishrussianflashcards.EspressoViewDataExtraction
import com.example.englishrussianflashcards.screenuielements.AutoCompleteListItemSelectionUi
import com.example.englishrussianflashcards.screenuielements.espresso.EspressoAutoCompleteListItemSelectionUi
import dagger.Module
import org.hamcrest.Matchers.instanceOf
import com.example.englishrussianflashcards.createcard.presentation.R
import com.example.englishrussianflashcards.screenuielements.espresso.EspressoClickableUi
import com.google.android.material.internal.CheckableImageButton
import dagger.Provides
import org.hamcrest.Matchers.allOf

/**
 * Created by Igor Aghibalov on 04.03.2026
 */

private const val TYPING_WORD_CHARACTERS = "ab"
private const val WORD_EXTRACTION_DESCRIPTION = "word extraction"

@Module
object AutoCompleteListItemSelectionUiModule {

    @Provides
    @EspressoWordSelectionUi
    fun provideWordSelectionUi(): AutoCompleteListItemSelectionUi<String> {
        return EspressoAutoCompleteListItemSelectionUi(
           EspressoListItemSelectionTask<String>(itemDataMatcher = instanceOf(String::class.java),
                                                 itemPosition = 0),
           typingCharacters = TYPING_WORD_CHARACTERS,
           listShowTriggerId = R.id.word_auto_complete_text_view,
           wordExtraction = EspressoViewDataExtraction(defaultDataValue = DEFAULT_STRING,
                                                       dataViewId = R.id.word_auto_complete_text_view,
                                                       dataViewMatcher = instanceOf(String::class.java),
                                                       dataExtractionDescription = WORD_EXTRACTION_DESCRIPTION,
                                                       dataExtractionLambda =  { view -> (view as TextView).text.toString() }),
           textClearingUi = EspressoClickableUi(allOf(instanceOf(CheckableImageButton::class.java),
                                                      isDisplayed(),
                                                      ClearButtonOwnerMatcher(ownerId = R.id.word_auto_complete_text_view))))
    }
}