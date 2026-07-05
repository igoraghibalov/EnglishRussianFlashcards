package com.example.englishrussianflashcards.di.hilt

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.appscreens.screenuielements.ButtonUi
import com.example.englishrussianflashcards.appscreens.screenuielements.espresso.EspressoButtonUi
import com.example.englishrussianflashcards.appscreens.screenuielements.espresso.EspressoClickableUi
import com.example.englishrussianflashcards.createcard.presentation.R as CreateCardModuleR
import com.example.englishrussianflashcards.R as MainModuleR
import dagger.Module
import dagger.Provides

/**
 * Created by Igor Aghibalov on 29.03.2026
 */
@Module
object ButtonUiModule {

    @EspressoCardCreationButtonUi
    @Provides
    fun provideCardCreationButtonUi(): ButtonUi {

        return EspressoButtonUi(EspressoClickableUi(clickableViewMatcher = withId(
            CreateCardModuleR.id.card_creation_button)))
    }


    @EspressoMainScreenContinueButtonUi
    @Provides
    fun provideMainScreenContinueButtonUi(): ButtonUi {

        return EspressoButtonUi(EspressoClickableUi(clickableViewMatcher = withId(
            MainModuleR.id.continue_button)))
    }


    @EspressoMainScreenCardsButtonUi
    @Provides
    fun provideMainScreenCardsButtonUi(): ButtonUi {

        return EspressoButtonUi(EspressoClickableUi(clickableViewMatcher = withId(
            MainModuleR.id.cards_button)))
    }


    @EspressoMainScreenNewCardButtonUi
    @Provides
    fun provideMainScreenNewCardButtonUi(): ButtonUi {

        return EspressoButtonUi(EspressoClickableUi(clickableViewMatcher = withId(
            MainModuleR.id.create_card_button)))
    }


    @EspressoMainScreenHistoryButtonUi
    @Provides
    fun provideMainScreenHistoryButtonUi(): ButtonUi {

        return EspressoButtonUi(EspressoClickableUi(clickableViewMatcher = withId(
            MainModuleR.id.history_button)))
    }


    @EspressoMainScreenQuitButtonUi
    @Provides
    fun provideMainScreenQuitButtonUi(): ButtonUi {

        return EspressoButtonUi(EspressoClickableUi(clickableViewMatcher = withId(
            MainModuleR.id.quit_button)))
    }


    @EspressoGroupsButtonUi
    @Provides
    fun provideGroupsButtonUi(): ButtonUi {

        return EspressoButtonUi(EspressoClickableUi(clickableViewMatcher = withId(
            CreateCardModuleR.id.groups_button)))
    }


    @EspressoAddGroupButtonUi
    @Provides
    fun provideAddGroupButtonUi(): ButtonUi {

        return EspressoButtonUi(EspressoClickableUi(clickableViewMatcher = withId(
            CreateCardModuleR.id.add_group_button)))
    }
}