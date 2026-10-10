package com.example.englishrussianflashcards.tests.features.cardcreation


import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest

/**
 * Created by Igor Aghibalov on 04.10.2026
 */
@HiltAndroidTest
open class BaseDictionaryRepositoryErrorUiTest: BaseErrorUiTest() {

    @BindValue
    @JvmField
    val fakeSuccessImageRepository = FakeSuccessImageRepository()


    @BindValue
    @JvmField
    val fakeSuccessCardGroupTitleRepository = FakeSuccessCardGroupTitleRepository()
}