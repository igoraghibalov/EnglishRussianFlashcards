package com.example.englishrussianflashcards

import org.junit.Test

/**
 * Created by Igor Aghibalov on 22.04.2024
 */
class MainScreenUiTester: UiTester() {
    private lateinit var clickedViewToInflatedFragmentViewMap: Map<Int, Int>
    private lateinit var fragmentInflationTestHandler: FragmentInflationTestHandler

    override fun setup() {
        super.setup()
        clickedViewToInflatedFragmentViewMap = mapOf(R.id.new_button to R.id.word_typing_text_view,
                                                     R.id.continue_button to R.id.card_view_pager,
                                                     R.id.cards_button to R.id.card_group_recycler_view,
                                                     R.id.history_button to R.id.card_history_recycler_view)
        fragmentInflationTestHandler = MainScreenFragmentsInflationTestHandler(clickedViewToInflatedFragmentViewMap)
    }


    @Test
    fun testFragmentInflationsOnMainMenuButtonsClicks() {
        testFragmentInflation(fragmentInflationTestHandler)
    }


}