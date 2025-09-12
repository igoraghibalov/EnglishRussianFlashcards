package com.example.englishrussianflashcards

import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Created by Igor Aghibalov on 12.09.2025
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(CardCreationSuccessUiTest::class,
                    CardCreationDictionaryRepositoryErrorUiTest::class,
                    CardCreationImageRepositoryServerErrorUiTest::class,
                    CardCreationImageRepositoryNetworkConnectionErrorUiTest::class)
class CardCreationUiTestSuite {
}