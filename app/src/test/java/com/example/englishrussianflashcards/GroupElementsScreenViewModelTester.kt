package com.example.englishrussianflashcards

import android.database.sqlite.SQLiteException
import androidx.lifecycle.MutableLiveData
import com.example.englishrussianflashcards.domain.Card
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Created by Igor Aghibalov on 13.07.2024
 */
class GroupElementsScreenViewModelTester: ViewModelTester() {
    private lateinit var fakeCardList: List<Card>
    private val cardGroupName = "Fruits"


    override fun setupTestEnvironment() {
        val fakeCard = Card(id = 0,
            word = "apple",
            transcription = "[]",
            translation = "яблоко",
            isDisplayed =  true,
            group = "Fruits")

        fakeCardList = listOf(fakeCard)
        val cardListMutableLiveData = MutableLiveData<List<Card>>()
        liveDataWrapper = CardListLiveDataWrapper(cardListMutableLiveData)
    }


    @Test
    fun testCardListExtractionByGroupSuccess() {
        testCase(CardExtractionByGroupViewModelTestHandler(viewModel, fakeCardList, cardGroupName))
    }

    @Test
    fun testCardListExtractionByGroupFailure() {
        testCase(CardExtractionByGroupViewModelTestHandler(viewModel, SQLiteException(), cardGroupName))
    }
}