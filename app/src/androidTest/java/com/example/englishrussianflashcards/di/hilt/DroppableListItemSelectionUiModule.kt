package com.example.englishrussianflashcards.di.hilt

import android.widget.ImageView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.DEFAULT_STRING
import com.example.englishrussianflashcards.EspressoRecyclerViewItemSelectionTask
import com.example.englishrussianflashcards.EspressoViewDataExtraction
import com.example.englishrussianflashcards.screenuielements.DroppableListItemSelectionUi
import com.example.englishrussianflashcards.screenuielements.espresso.EspressoClickableUi
import com.example.englishrussianflashcards.screenuielements.espresso.EspressoDroppableListItemSelectionUi
import com.example.englishrussianflashcards.createcard.presentation.R
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
    fun provideImageSelectionUi(): DroppableListItemSelectionUi<String> {

        return EspressoDroppableListItemSelectionUi<String, ImageRecyclerViewAdapter.ImageViewHolder>(
                            selectionDropUi = EspressoClickableUi(withId(R.id.image_drop_button)),
                            showListUi = EspressoClickableUi(withId(R.id.image_button)),
                            itemSelectionTask = EspressoRecyclerViewItemSelectionTask<ImageRecyclerViewAdapter.ImageViewHolder>(
                                                        recyclerViewId = R.id.image_recycler_view,
                                                        itemPosition = 0,
                                                        itemViewAction = click()),
                            selectedItemExtraction = EspressoViewDataExtraction(
                                                        defaultDataValue = DEFAULT_STRING,
                                                        dataViewId = R.id.selected_image_view,
                                                        dataViewMatcher = isAssignableFrom(ImageView::class.java),
                                                        dataExtractionDescription = IMAGE_URL_TAG_EXTRACTION_DESCRIPTION,
                                                        dataExtractionLambda = { val imageUrlTag = it.tag
                                                                                 if (imageUrlTag == null
                                                                                                 || imageUrlTag !is String
                                                                                                 || !imageUrlTag.matches(Regex(URL_PATTERN))) {
                                                                                     throw ImageUrlTagException()
                                                                                 }; imageUrlTag })))
    }
}


class ImageUrlTagException: RuntimeException()