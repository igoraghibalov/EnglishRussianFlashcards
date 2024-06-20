package com.example.englishrussianflashcards

import com.example.englishrussianflashcards.domain.ApplicationDatabase
import com.example.englishrussianflashcards.domain.Card
import com.example.englishrussianflashcards.domain.CardDao
import com.example.englishrussianflashcards.domain.DictionaryDao
import kotlin.coroutines.CoroutineContext

/**
 * Created by Igor Aghibalov on 20.06.2024
 */
abstract class DatabaseTester: Tester() {
    private lateinit var applicationDatabase: ApplicationDatabase
    private lateinit var additionalTestCoroutineContext: CoroutineContext

}