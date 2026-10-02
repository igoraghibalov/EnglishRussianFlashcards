package com.example.englishrussianflashcards.tests.features.cardcreation

import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Created by Igor Aghibalov on 12.09.2025
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(SuccessUiTest::class,
                    DictionaryRepositoryErrorUiTest::class,
                    ImageRepositoryServerErrorUiTest::class,
                    ImageRepositoryNetworkConnectionErrorUiTest::class,
                    CardRepositoryErrorDialogShowOnCreateCardButtonClickUiTest::class,
                    DuplicatedCardAppendingAttemptErrorUiTest::class)
class TestSuite