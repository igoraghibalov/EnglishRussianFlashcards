package com.example.englishrussianflashcards.di.hilt

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.EspressoListItemSelectionTask
import com.example.englishrussianflashcards.appscreens.screenuielements.ListItemSelectionUi
import com.example.englishrussianflashcards.appscreens.screenuielements.espresso.EspressoClickableUi
import com.example.englishrussianflashcards.appscreens.screenuielements.espresso.EspressoListItemSelectionUi
import com.example.englishrussianflashcards.createcard.presentation.R
import dagger.Module
import dagger.Provides
import org.hamcrest.Matchers.instanceOf


/**
 * Created by Igor Aghibalov on 08.03.2026
 */
@Module
object ListItemSelectionUiModule {

    @Provides
    @EspressoTranslationSelectionUi
    fun provideTranslationSelectionUi(): ListItemSelectionUi {

        return EspressoListItemSelectionUi(
                        listItemSelectionTask = EspressoListItemSelectionTask<String>(
                                                        itemDataMatcher = instanceOf(String::class.java),
                                                        itemPosition = 0),
                        listShowUi = EspressoClickableUi(clickableViewMatcher = withId(R.id.translation_spinner)))
    }


    @Provides
    @EspressoExampleSelectionUi
    fun provideExampleSelectionUi(): ListItemSelectionUi {

        return EspressoListItemSelectionUi(
                        listItemSelectionTask = EspressoListItemSelectionTask<String>(
                                                        itemDataMatcher = instanceOf(String::class.java),
                                                        itemPosition = 0),
                        listShowUi = EspressoClickableUi(clickableViewMatcher = withId(R.id.example_spinner)))
    }
}