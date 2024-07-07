package com.example.englishrussianflashcards.testers

import com.example.englishrussianflashcards.domain.ApplicationDatabase
import kotlin.coroutines.CoroutineContext

/**
 * Created by Igor Aghibalov on 20.06.2024
 */
abstract class DatabaseTester: Tester() {
    protected lateinit var applicationDatabase: ApplicationDatabase
    protected lateinit var additionalTestCoroutineContext: CoroutineContext

}