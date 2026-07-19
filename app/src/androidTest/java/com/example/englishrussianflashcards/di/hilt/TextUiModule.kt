package com.example.englishrussianflashcards.di.hilt

import android.content.Context
import android.widget.TextView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.englishrussianflashcards.Card
import com.example.englishrussianflashcards.appscreens.screenuielements.TextUi
import com.example.englishrussianflashcards.appscreens.screenuielements.espresso.EspressoTextUi
import com.example.englishrussianflashcards.createcard.presentation.R
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

/**
 * Created by Igor Aghibalov on 08.03.2026
 */

private const val DEFAULT_TRANSCRIPTION_PATTERN = "\\[.{3}]"
private const val TRANSCRIPTION_MATCH_DESCRIPTION = "matching default transcription"
private const val CARD_GROUP_NAME_MATCH_DESCRIPTION = "matching card group name"


@Module
object TextUiModule {

    @Provides
    @EspressoTranscriptionUi
    fun provideTranscriptionUi(): TextUi {

        return EspressoTextUi(textViewMatcher = withId(R.id.transcription_text_view),
                              textMatcher = withText(TextViewStringMatcher(pattern = RegexPattern(Regex(DEFAULT_TRANSCRIPTION_PATTERN)),
                                                                           stringMatchingDescription = TRANSCRIPTION_MATCH_DESCRIPTION)),)
    }


    @Provides
    @EspressoGroupNameUi
    fun provideGroupNameUi(@ApplicationContext context: Context): TextUi {

        val expectedCardStream = context.assets.open("expected_card_data.json")
        val expectedCardAsString = expectedCardStream.bufferedReader().use { it.readText() }
        val expectedCard = Json.decodeFromString<Card>(expectedCardAsString)
        val cardGroupName = expectedCard.cardGroupName

        return EspressoTextUi(textViewMatcher = withId(R.id.card_group_screen_title_text_view),
                              textMatcher = withText(TextViewStringMatcher(pattern = RegexPattern(Regex(cardGroupName)),
                                                                           stringMatchingDescription = CARD_GROUP_NAME_MATCH_DESCRIPTION)),)
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
