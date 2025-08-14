package com.example.customviews

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.LinearLayout
import com.example.englishrussianflashcards.customviews.R

/**
 * Created by Igor Aghibalov on 12.08.2025
 */
@SuppressLint("ClickableViewAccessibility")
class InputConfirmingAutoCompleteTextView: LinearLayout {

    constructor(context: Context): super(context)
    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int)
            : super(context, attributeSet, defStyleAttr)


    init {
        inflate(context, R.layout.input_confirming_auto_complete_text_view_layout, this)

        val okButton = findViewById<Button>(R.id.ok_button)
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.auto_complete_text_view)
        val xButton = autoCompleteTextView.compoundDrawables[2]!!
        var xButtonAlpha = xButton.alpha

        xButtonAlpha = 0

        autoCompleteTextView.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                xButtonAlpha = 255
            }

            override fun afterTextChanged(s: Editable?) {
                if (!autoCompleteTextView.isPopupShowing) {
                    okButton.visibility = VISIBLE
                }
            }
        })

        autoCompleteTextView.setOnTouchListener(object: OnTouchListener {

            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                return if (event!!.action == MotionEvent.ACTION_UP
                                    && event.rawX >= (autoCompleteTextView.right - xButton.minimumWidth)) {

                    xButtonAlpha = 0
                    okButton.visibility = INVISIBLE

                    true
                } else {

                    false
                }
            }
        })
    }
}