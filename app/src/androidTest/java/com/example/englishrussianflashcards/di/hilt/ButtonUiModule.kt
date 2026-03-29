package com.example.englishrussianflashcards.di.hilt

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.screenuielements.ButtonUi
import com.example.englishrussianflashcards.screenuielements.espresso.EspressoButtonUi
import com.example.englishrussianflashcards.screenuielements.espresso.EspressoClickableUi
import com.example.englishrussianflashcards.createcard.presentation.R
import dagger.Module
import dagger.Provides

/**
 * Created by Igor Aghibalov on 29.03.2026
 */
@Module
object ButtonUiModule {

    @EspressoCardCreationButtonUi
    @Provides
    fun provideСardCreationButtonUi(): ButtonUi {

        return EspressoButtonUi(EspressoClickableUi(clickableViewMatcher = withId(R.id.card_creation_button)))
    }
}