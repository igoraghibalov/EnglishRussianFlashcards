package com.example.englishrussianflashcards

import android.app.Application
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Test

/**
 * Created by Igor Aghibalov on 22.04.2024
 */
class MainScreenUiTester: UiTester() {

    private lateinit var mainActivityScenario: ActivityScenario<MainActivity>


    override fun rotateScreen() {
        TODO("Not yet implemented")
    }

    override fun testFragmentInflation(baseLayoutViewId: Int, inflatedFragmentViewId: Int) {
        super.testFragmentInflation(baseLayoutViewId, inflatedFragmentViewId)
        mainActivityScenario.recreate()
    }

    @Test
    fun testFragmentInflationsOnMainMenuButtonsClicks() {
        testFragmentInflation(R.id.new_button, R.id.word_typing_text_view)
        testFragmentInflation(R.id.continue_button, R.id.flashcard_front_side_view)
        testFragmentInflation(R.id.cards_button, R.id.card_group_recycler_view)
        testFragmentInflation(R.id.history_button, R.id.card_history_recycler_view)
    }
}