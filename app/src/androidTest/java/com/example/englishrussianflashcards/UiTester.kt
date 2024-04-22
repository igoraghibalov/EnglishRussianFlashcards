package com.example.englishrussianflashcards

/**
 * Created by Igor Aghibalov on 22.04.2024
 */
interface UiTester {
    fun clickOnView(viewId: Int)
    fun rotateScreen()
    fun checkViewAppearance(viewId: Int)
}