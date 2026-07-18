package com.example.englishrussianflashcards.appscreens.screenuielements.espresso

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import com.example.englishrussianflashcards.RecyclerViewItemSelectionTask
import com.example.englishrussianflashcards.appscreens.screenuielements.ClickableUi
import com.example.englishrussianflashcards.appscreens.screenuielements.ImageUi
import com.example.englishrussianflashcards.appscreens.screenuielements.MenuUi
import org.hamcrest.Matcher

/**
 * Created by Igor Aghibalov on 28.02.2026
 */
class EspressoImageUi<VH: RecyclerView.ViewHolder>(
                private val imageListShowButtonUi: ClickableUi,
                private val imageListUi: MenuUi,
                private val imageSelectionTask: RecyclerViewItemSelectionTask<VH>,
                private val selectedImageDropButtonUi: ClickableUi,
                private val selectedImageContainerUi: ImageContainerUi)
    : ImageUi {


    override fun dropSelection() {
        selectedImageDropButtonUi.click()
    }

    override fun showMenu() {
        imageListShowButtonUi.click()
    }

    override fun selectItem() {
        imageSelectionTask.run()
    }

    override fun checkItemPresence() {
        selectedImageContainerUi.checkImagePresence()
    }

    override fun scrollToItem() {
        imageListUi.scrollToItem()
    }
}


interface ImageContainerUi {
    fun checkImagePresence()
}


class EspressoImageContainerUi(private val imageViewMatcher: Matcher<View>,
                               private val selectedImageMatcher: Matcher<View>): ImageContainerUi {

    override fun checkImagePresence() {
        onView(imageViewMatcher).check(matches(selectedImageMatcher))
    }
}