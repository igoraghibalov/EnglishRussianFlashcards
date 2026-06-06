package com.example.englishrussianflashcards.di.hilt

import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.ClearButtonOwnerMatcher
import com.example.englishrussianflashcards.EspressoListItemSelectionTask
import com.example.englishrussianflashcards.appscreens.screenuielements.AutoCompleteListItemSelectionUi
import com.example.englishrussianflashcards.appscreens.screenuielements.espresso.EspressoAutoCompleteListItemSelectionUi
import com.example.englishrussianflashcards.appscreens.screenuielements.espresso.EspressoClickableUi
import com.example.englishrussianflashcards.createcard.presentation.R
import com.google.android.material.internal.CheckableImageButton
import dagger.Module
import dagger.Provides
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf

/**
 * Created by Igor Aghibalov on 04.03.2026
 */

private const val TYPING_WORD_CHARACTERS = "ap"
private const val TYPING_CARD_GROUP_CHARACTERS = "fr"
private const val WORD_EXTRACTION_DESCRIPTION = "word extraction"
private const val CARD_GROUP_EXTRACTION_DESCRIPTION = "card group title extraction"

@Module
object AutoCompleteListItemSelectionUiModule {

    @Provides
    @EspressoWordSelectionUi
    fun provideWordSelectionUi(): AutoCompleteListItemSelectionUi {
        return EspressoAutoCompleteListItemSelectionUi(
           listItemSelectionTask = EspressoListItemSelectionTask<String>(itemDataMatcher = instanceOf(String::class.java),
                                                 itemPosition = 0),
           characterTypeAction = typeText(TYPING_WORD_CHARACTERS),
           autoCompleteListItemSelectionUiMatcher = withId(R.id.word_auto_complete_text_view),
           textClearingUi = EspressoClickableUi(allOf(instanceOf(CheckableImageButton::class.java),
                                                      isDisplayed(),
                                                      ClearButtonOwnerMatcher(ownerId = R.id.word_auto_complete_text_view))))
    }


    @Provides
    @EspressoCardGroupTitleSelectionUi
    fun provideCardGroupTitleSelectionUi(): AutoCompleteListItemSelectionUi {
        return EspressoAutoCompleteListItemSelectionUi(
           listItemSelectionTask = EspressoListItemSelectionTask<String>(itemDataMatcher = instanceOf(String::class.java),
                                                                         itemPosition = 0),
           characterTypeAction = typeText(TYPING_CARD_GROUP_CHARACTERS),
           autoCompleteListItemSelectionUiMatcher = withId(R.id.card_group_auto_complete_text_view),
           textClearingUi = EspressoClickableUi(allOf(instanceOf(CheckableImageButton::class.java),
                                                      isDisplayed(),
                                                      ClearButtonOwnerMatcher(ownerId = R.id.card_group_auto_complete_text_view))))
    }
}