package com.example.englishrussianflashcards.casetesthandlers

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import com.example.englishrussianflashcards.CaseUiTestHandler
import com.example.englishrussianflashcards.R
import com.example.englishrussianflashcards.domain.ApplicationDatabase
import com.example.englishrussianflashcards.domain.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matcher
import org.hamcrest.Matchers

/**
 * Created by Igor Aghibalov on 26.06.2024
 */
/*TODO:
         * - Insert 2 fruit cards
         * - click cards button in a main menu
         * - assert: grid element contains fruit group name and 2 number - fruit cards count
         * - test ui behaviour on database connection fail
 */
class GroupCardsSuccessfulDisplayTestHandler(private val applicationContext: Context): CaseUiTestHandler() {


    override fun handleCaseTest() {
        val applicationDatabase = Room.inMemoryDatabaseBuilder(applicationContext, ApplicationDatabase::class.java)
            .build()

        val cardDao = applicationDatabase.getCardDao()

        val appleCard = Card(id = 0,
            word = "apple",
            translation = "яблоко",
            transcription = "[ˈæpəl]",
            group = "Fruits",
            isDisplayed = false)
        val orangeCard = Card(id = 1,
            word = "orange",
            translation = "апельсин",
            transcription = "[ˈɔːrəndʒ]",
            group = "Fruits",
            isDisplayed = false)

        runBlocking {


            val cardInsertionJob = launch(Dispatchers.Default) {
                cardDao.insertCard(appleCard)
                launch { cardDao.insertCard(orangeCard) }
            }

            cardInsertionJob.join()

        }


    }


    override fun doAfterCaseTestHandling() {}


    fun isGroupCardPresent(groupName: String,
                           groupElementsCount: Int): Boolean {
        var isGroupCardPresent: Boolean = false
        val cardGroupRecyclerViewInteraction = onView(ViewMatchers.withId(R.id.card_group_recycler_view))

        val groupCardPresenceCheckAction = object: ViewAction {

            override fun getDescription(): String =
                "Performing group card presence check action"


            override fun getConstraints(): Matcher<View> {
                return Matchers.allOf(
                    ViewMatchers.isAssignableFrom(RecyclerView::class.java),
                    ViewMatchers.isDisplayed()
                )
            }


            override fun perform(uiController: UiController?, view: View?) {
                val recyclerView = view as RecyclerView
                val layoutManager = recyclerView.layoutManager
                val childCount = layoutManager!!.childCount
                var childView: CardView

                for (childIndex in 0 until childCount) {
                    childView =
                        layoutManager.getChildAt(childIndex)!! as CardView
                    val groupNameView =
                        childView.findViewById<TextView>(R.id.word_group_name_text_view)
                    val wordCountView =
                        childView.findViewById<TextView>(R.id.word_count_text_view)
                    if (groupNameView.text == groupName
                        && wordCountView.text == groupElementsCount.toString()
                    ) {
                        isGroupCardPresent = true
                        break
                    }
                }
            }
        }
        cardGroupRecyclerViewInteraction.perform(groupCardPresenceCheckAction)
        return isGroupCardPresent
    }
}