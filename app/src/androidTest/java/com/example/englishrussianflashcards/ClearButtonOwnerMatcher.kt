package com.example.englishrussianflashcards

import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.internal.CheckableImageButton
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description

/**
 * Created by Igor Aghibalov on 07.03.2026
 */
class ClearButtonOwnerMatcher(private val ownerId: Int)
    : BoundedMatcher<View, CheckableImageButton>(CheckableImageButton::class.java) {

    override fun matchesSafely(item: CheckableImageButton?): Boolean {

        var parent: ViewParent = item!!.parent

        while (parent != null) {
            parent = parent.parent

            if (parent != null && parent::class.java == TextInputLayout::class.java) {
                return (parent as ViewGroup).findViewById<MaterialAutoCompleteTextView>(ownerId) != null

            }
        }
        return false
    }

    override fun describeTo(description: Description?) {
        description!!.appendText("matches clear button owner")
    }
}