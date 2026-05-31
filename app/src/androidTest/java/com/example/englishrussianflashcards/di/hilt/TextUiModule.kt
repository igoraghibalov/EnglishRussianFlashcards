package com.example.englishrussianflashcards.di.hilt

import android.widget.TextView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.englishrussianflashcards.appscreens.screenuielements.TextUi
import com.example.englishrussianflashcards.appscreens.screenuielements.espresso.EspressoTextUi
import com.example.englishrussianflashcards.createcard.presentation.R
import dagger.Module
import dagger.Provides
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

/**
 * Created by Igor Aghibalov on 08.03.2026
 */

private const val TRANSCRIPTION_PATTERN = "^\\[[^0-9]+]$|translation not found"
private const val TRANSCRIPTION_MATCH_DESCRIPTION = "matching transcription"


@Module
object TextUiModule {

    @Provides
    @EspressoTranscriptionUi
    fun provideTranscriptionUi(): TextUi<String> {

        return EspressoTextUi(textViewMatcher = withId(R.id.transcription_text_view),
                              textMatcher = withText(TextViewStringMatcher(pattern = RegexPattern(Regex(TRANSCRIPTION_PATTERN)),
                                                                           stringMatchingDescription = TRANSCRIPTION_MATCH_DESCRIPTION)),)
    }
}


class TextViewStringMatcher(private val pattern: Pattern,
                            private val stringMatchingDescription: String): BaseMatcher<String>() {


    override fun matches(actual: Any?): Boolean {
        val text = (actual as TextView).text
        return pattern.matches(text)
    }


    override fun describeTo(description: Description?) {
        description!!.appendText(stringMatchingDescription)
    }
}


interface Pattern {
    fun matches(charSequence: CharSequence): Boolean
}


class RegexPattern(private val regex: Regex): Pattern {

    override fun matches(charSequence: CharSequence): Boolean {
        return charSequence.matches(regex)
    }
}
