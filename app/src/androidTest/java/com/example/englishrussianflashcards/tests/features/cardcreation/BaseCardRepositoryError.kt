package com.example.englishrussianflashcards.tests.features.cardcreation

import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest

/**
 * Created by Igor Aghibalov on 17.10.2026
 */
@HiltAndroidTest
open class BaseCardRepositoryError: BaseErrorUiTest() {


    @BindValue
    @JvmField
    val fakeSuccessDictionaryRepository = FakeSuccessDictionaryRepository()


    @BindValue
    @JvmField
    val fakeSuccessImageRepository = FakeSuccessImageRepository()
}