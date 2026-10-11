package com.example.englishrussianflashcards.tests.features.cardcreation

import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest

/**
 * Created by Igor Aghibalov on 10.10.2026
 */
@HiltAndroidTest
open class BaseImageRepositoryErrorUiTest: BaseErrorUiTest() {


        @BindValue
        @JvmField
        val fakeSuccessImageRepository = FakeSuccessDictionaryRepository()


        @BindValue
        @JvmField
        val fakeSuccessCardGroupTitleRepository = FakeSuccessCardGroupTitleRepository()
}