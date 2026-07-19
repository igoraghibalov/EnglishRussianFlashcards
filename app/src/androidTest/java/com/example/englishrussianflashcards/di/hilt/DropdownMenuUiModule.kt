package com.example.englishrussianflashcards.di.hilt

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.EspressoMenuItemSelectionTask
import com.example.englishrussianflashcards.appscreens.screenuielements.DropdownMenuUi
import com.example.englishrussianflashcards.appscreens.screenuielements.espresso.EspressoClickableUi
import com.example.englishrussianflashcards.appscreens.screenuielements.espresso.EspressoDropdownMenuUi
import com.example.englishrussianflashcards.createcard.presentation.R
import dagger.Module
import dagger.Provides
import org.hamcrest.core.IsInstanceOf.instanceOf


/**
 * Created by Igor Aghibalov on 08.03.2026
 */
@Module
object DropdownMenuUiModule {

    @Provides
    @EspressoTranslationUi
    fun provideTranslationSelectionUi(): DropdownMenuUi {

        return EspressoDropdownMenuUi(
                        menuItemSelectionTask = EspressoMenuItemSelectionTask<String>(
                                                        itemDataMatcher = instanceOf(String::class.java),
                                                        itemPosition = 0),
                        dropdownMenuShowUi = EspressoClickableUi(clickableViewMatcher = withId(R.id.translation_spinner)))
    }


    @Provides
    @EspressoExampleUi
    fun provideExampleSelectionUi(): DropdownMenuUi {

        return EspressoDropdownMenuUi(
                        menuItemSelectionTask = EspressoMenuItemSelectionTask<String>(
                                                        itemDataMatcher = instanceOf(String::class.java),
                                                        itemPosition = 0),
                        dropdownMenuShowUi = EspressoClickableUi(clickableViewMatcher = withId(R.id.example_spinner)))
    }
}