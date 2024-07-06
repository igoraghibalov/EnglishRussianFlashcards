package com.example.englishrussianflashcards.casetesthandlers.inflation

import com.example.englishrussianflashcards.CaseUiTestHandler

/**
 * Created by Igor Aghibalov on 06.07.2024
 */
class RecyclerViewDisplayTestHandler(private val recyclerViewId: Int): CaseUiTestHandler() {


    override fun handleCaseTest() {
        checkViewAppearance(recyclerViewId)
    }
}