package com.example.englishrussianflashcards.di.hilt

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.EspressoRecyclerViewItemSelectionTask
import com.example.englishrussianflashcards.appscreens.screenuielements.DroppableListItemSelectionUi
import com.example.englishrussianflashcards.appscreens.screenuielements.espresso.EspressoClickableUi
import com.example.englishrussianflashcards.appscreens.screenuielements.espresso.EspressoDroppableListItemSelectionUi
import com.example.englishrussianflashcards.createcard.presentation.R
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import org.hamcrest.Matchers.instanceOf
import androidx.cardview.widget.CardView
import dagger.Module
import dagger.Provides


private const val IMAGE_URL_TAG_EXTRACTION_DESCRIPTION = "image url tag extraction"
private const val URL_PATTERN = "^(https?|ftp)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"

/**
 * Created by Igor Aghibalov on 22.03.2026
 */
@Module
object DroppableListItemSelectionUiModule {

    @Provides
    @EspressoImageSelectionUi
    fun provideImageSelectionUi(): DroppableListItemSelectionUi {

        return EspressoDroppableListItemSelectionUi<ImageRecyclerViewAdapter.ImageViewHolder>(
                            selectionDropUi = EspressoClickableUi(withId(R.id.image_drop_button)),
                            showListUi = EspressoClickableUi(withId(R.id.image_button)),
                            itemSelectionTask = EspressoRecyclerViewItemSelectionTask<ImageRecyclerViewAdapter.ImageViewHolder>(
                                                        recyclerViewMatcher = withId(R.id.image_recycler_view),
                                                        itemViewMatcher = instanceOf(CardView::class.java),
                                                        itemViewAction = click()))
    }
}