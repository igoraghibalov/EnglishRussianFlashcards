package com.example.englishrussianflashcards.appscreens

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.englishrussianflashcards.Card
import com.example.englishrussianflashcards.RecyclerViewCardPresenceMatcher
import javax.inject.Inject
import com.example.englishrussianflashcards.createcard.presentation.R


/**
 * Created by Igor Aghibalov on 12.04.2026
 */
class CardGroupScreen @Inject constructor(
    @ExpectedCardOnCreationTest private val createdCardToBeChecked: Card) {

    fun checkCreatedCardPresence() {
        val cardRecyclerViewMatcher = onView(withId(R.id.card_recycler_view))
        cardRecyclerViewMatcher.perform(scrollTo<CardViewHolder>(
            RecyclerViewCardPresenceMatcher(createdCardToBeChecked)))
    }

    fun clickAddCardButton() {}
    fun checkCardDuplication() {}
    fun clickGroupsButton() {}
    fun hasCorrectGroupTitle() {}
    fun selectCard() {}
    fun checkDatabaseErrorDialogPresence() {}
    fun closeDatabaseErrorDialog() {}
}

class CardViewHolder(private val listItemCardLayoutBinding: ListItemCardLayoutBinding):
    RecyclerView.ViewHolder(listItemCardLayoutBinding.root) {

}