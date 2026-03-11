package com.example.englishrussianflashcards.di.hilt

import android.widget.TextView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.DEFAULT_STRING
import com.example.englishrussianflashcards.EspressoListItemSelectionTask
import com.example.englishrussianflashcards.EspressoViewDataExtraction
import com.example.englishrussianflashcards.createcard.presentation.R
import com.example.englishrussianflashcards.screenuielements.ListItemSelectionUi
import com.example.englishrussianflashcards.screenuielements.espresso.EspressoClickableUi
import com.example.englishrussianflashcards.screenuielements.espresso.EspressoListItemSelectionUi
import dagger.Module
import dagger.Provides
import org.hamcrest.Matchers.instanceOf


private const val TRANSLATION_EXTRACTION_DESCRIPTION = "translation extraction"
private const val EXAMPLE_EXTRACTION_DESCRIPTION = "example extraction"


/**
 * Created by Igor Aghibalov on 08.03.2026
 */
@Module
object ListItemSelectionUiModule {

    @Provides
    @EspressoTranslationSelectionUi
    fun provideTranslationSelectionUi(): ListItemSelectionUi<String> {

        return EspressoListItemSelectionUi(
                        listItemSelectionTask = EspressoListItemSelectionTask<String>(
                                                        itemDataMatcher = instanceOf(String::class.java),
                                                        itemPosition = 0),
                        viewDataExtraction = EspressoViewDataExtraction(
                                                    defaultDataValue = DEFAULT_STRING,
                                                    dataViewId = R.id.translation_spinner,
                                                    dataViewMatcher = instanceOf(String::class.java),
                                                    dataExtractionDescription = TRANSLATION_EXTRACTION_DESCRIPTION,
                                                    dataExtractionLambda =  { view -> (view as TextView).text.toString() }),
                        listShowUi = EspressoClickableUi(clickableViewMatcher = withId(R.id.translation_spinner)))
    }


    @Provides
    @EspressoExampleSelectionUi
    fun provideExampleSelectionUi(): ListItemSelectionUi<String> {

        return EspressoListItemSelectionUi(
                        listItemSelectionTask = EspressoListItemSelectionTask<String>(
                                                        itemDataMatcher = instanceOf(String::class.java),
                                                        itemPosition = 0),
                        viewDataExtraction = EspressoViewDataExtraction(
                                                    defaultDataValue = DEFAULT_STRING,
                                                    dataViewId = R.id.example_spinner,
                                                    dataViewMatcher = instanceOf(String::class.java),
                                                    dataExtractionDescription = EXAMPLE_EXTRACTION_DESCRIPTION,
                                                    dataExtractionLambda =  { view -> (view as TextView).text.toString() }),
                        listShowUi = EspressoClickableUi(clickableViewMatcher = withId(R.id.example_spinner)))
    }
}