package com.example.englishrussianflashcards.domain

import android.graphics.drawable.Drawable

/**
 * Created by Igor Aghibalov on 30.05.2024
 */
interface ImageExtractor {
    fun getImageList(): List<Drawable>
}