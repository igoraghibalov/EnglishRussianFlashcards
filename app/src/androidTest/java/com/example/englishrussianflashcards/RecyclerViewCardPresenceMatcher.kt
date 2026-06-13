package com.example.englishrussianflashcards

import android.view.ViewParent
import androidx.cardview.widget.CardView
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

/**
 * Created by Igor Aghibalov on 13.06.2026
 */
class RecyclerViewCardPresenceMatcher(private val card: Card): TypeSafeMatcher<CardView>(CardView::class.java) {

    override fun matchesSafely(cardView: CardView?): Boolean {
        val rootView = provideRootView(cardView!!)
        val cardLayoutBinding = CardLayoutBinding.bind(rootView)

        return with(cardLayoutBinding) {
                    word == card.word &&
                    transcription == card.transcription &&
                    translation == card.translation &&
                    example == card.example &&
                    imageTag == card.imageTag &&
                    cardGroupName == card.cardGroupName
        }
    }

    override fun describeTo(description: Description?) {
        description!!.appendText("$card is present in a recyclerView")
    }

    fun provideRootView(view: CardView): ViewParent {
        val rootViewName = "com.android.internal.policy.DecorView"
        var viewParent: ViewParent = view.parent

        while (viewParent.javaClass.name != rootViewName) {
            viewParent = viewParent.parent
        }
        return viewParent
    }
}