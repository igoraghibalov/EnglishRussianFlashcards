package com.example.englishrussianflashcards

import android.view.View
import android.view.ViewParent
import androidx.cardview.widget.CardView
import com.example.englishrussianflashcards.createcard.presentation.databinding.ListItemCardLayoutBinding
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

private const val CARD_LAYOUT_PARENT_VIEW_NAME = "MaterialCardView"

/**
 * Created by Igor Aghibalov on 13.06.2026
 */
class RecyclerViewCardPresenceMatcher<T: View>(private val card: Card)
            : TypeSafeMatcher<T>(CardView::class.java) {

    override fun matchesSafely(cardView: T?): Boolean {
        val rootView = provideCardLayoutParentView(cardView!!)
        val listItemCardLayoutBinding = ListItemCardLayoutBinding.bind(rootView)

        return with(listItemCardLayoutBinding) {
                    word.text == card.word &&
                    transcription.text == card.transcription &&
                    translation.text == card.translation &&
                    example.text == card.example &&
                    image.tag == card.imageTag &&
                    cardGroupName.text == card.cardGroupName
        }
    }


    override fun describeTo(description: Description?) {
        description!!.appendText("matches card presence in a recyclerView")
    }


    fun provideCardLayoutParentView(view: T): View {
        val cardLayoutParentViewName = view.resources.getString(R.string.card_layout_parent_view_name)
        var viewParent: ViewParent = view.parent

        while (!viewParent.javaClass.name.contains(cardLayoutParentViewName, true)) {
            viewParent = viewParent.parent
        }
        return viewParent as View
    }
}