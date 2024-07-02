package com.example.englishrussianflashcards.casetesthandlers.inflation

import android.content.Context
import androidx.room.Room
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import com.example.englishrussianflashcards.CaseUiTestHandler
import com.example.englishrussianflashcards.GroupCardPresenceCheckAction
import com.example.englishrussianflashcards.R
import com.example.englishrussianflashcards.domain.ApplicationDatabase
import com.example.englishrussianflashcards.domain.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by Igor Aghibalov on 26.06.2024
 */
//TODO: test ui behaviour on database connection fail
class GroupCardsSuccessfulDisplayTestHandler(private val applicationContext: Context): CaseUiTestHandler() {


    override fun handleCaseTest() {
        val groupName = "Fruits"
        val  groupElementsCount = 2
        val applicationDatabase = Room.inMemoryDatabaseBuilder(applicationContext,
                                                               ApplicationDatabase::class.java)
                                      .build()

        val cardDao = applicationDatabase.getCardDao()

        val groupCardRecyclerViewInteraction = onView(ViewMatchers.withId(R.id.card_group_recycler_view))
        val groupCardPresenceCheckAction = GroupCardPresenceCheckAction(groupName, groupElementsCount)

        val appleCard = Card(id = 0,
            word = "apple",
            translation = "яблоко",
            transcription = "[ˈæpəl]",
            group = groupName,
            isDisplayed = false)
        val orangeCard = Card(id = 1,
            word = "orange",
            translation = "апельсин",
            transcription = "[ˈɔːrəndʒ]",
            group = groupName,
            isDisplayed = false)

        runBlocking {


            val cardInsertionJob = launch(Dispatchers.Default) {
                cardDao.insertCard(appleCard)
                launch { cardDao.insertCard(orangeCard) }
            }

            cardInsertionJob.join()
        }
        clickOnView(R.id.cards_button)
        groupCardRecyclerViewInteraction.perform(groupCardPresenceCheckAction)
    }
}