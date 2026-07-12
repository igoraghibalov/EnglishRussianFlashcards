package com.example.englishrussianflashcards.di.hilt

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.appscreens.screenuielements.ClickableUi
import com.example.englishrussianflashcards.appscreens.screenuielements._root_ide_package_.com.example.englishrussianflashcards.appscreens.screenuielements.ClickableUi
import com.example.englishrussianflashcards.appscreens.screenuielements.espresso.EspressoClickableUi
import com.example.englishrussianflashcards.appscreens.screenuielements.espresso._root_ide_package_.com.example.englishrussianflashcards.appscreens.screenuielements.espresso.EspressoClickableUi
import com.example.englishrussianflashcards.appscreens.screenuielements.espresso.import com.example.englishrussianflashcards.createcard.presentation.R as CreateCardModuleR
import com.example.englishrussianflashcards.R as MainModuleR
import dagger.Module
import dagger.Provides

/**
 * Created by Igor Aghibalov on 29.03.2026
 */
@Module
object ClickableUiModule {

    @EspressoCardCreationButtonUi
    @Provides
    fun provideCardCreationButtonUi(): ClickableUi {

        return EspressoClickableUi(clickableViewMatcher = withId(
            CreateCardModuleR.id.card_creation_button))
    }


    @EspressoMainScreenContinueButtonUi
    @Provides
    fun provideMainScreenContinueButtonUi(): ClickableUi {

        return EspressoClickableUi(clickableViewMatcher = withId(
            MainModuleR.id.continue_button))
    }


    @EspressoMainScreenCardsButtonUi
    @Provides
    fun provideMainScreenCardsButtonUi(): ClickableUi {

        return EspressoClickableUi(clickableViewMatcher = withId(
            MainModuleR.id.cards_button))
    }


    @EspressoMainScreenNewCardButtonUi
    @Provides
    fun provideMainScreenNewCardButtonUi(): ClickableUi {

        return EspressoClickableUi(clickableViewMatcher = withId(
            MainModuleR.id.create_card_button))
    }


    @EspressoMainScreenHistoryButtonUi
    @Provides
    fun provideMainScreenHistoryButtonUi(): ClickableUi {

        return EspressoClickableUi(clickableViewMatcher = withId(
            MainModuleR.id.history_button))
    }


    @EspressoMainScreenQuitButtonUi
    @Provides
    fun provideMainScreenQuitButtonUi(): ClickableUi {

        return EspressoClickableUi(clickableViewMatcher = withId(
            MainModuleR.id.quit_button))
    }


    @EspressoGroupsButtonUi
    @Provides
    fun provideGroupsButtonUi(): ClickableUi {

        return EspressoClickableUi(clickableViewMatcher = withId(
            CreateCardModuleR.id.groups_button))
    }


    @EspressoAddGroupButtonUi
    @Provides
    fun provideAddGroupButtonUi(): ClickableUi {

        return EspressoClickableUi(clickableViewMatcher = withId(
            CreateCardModuleR.id.add_group_button))
    }
}