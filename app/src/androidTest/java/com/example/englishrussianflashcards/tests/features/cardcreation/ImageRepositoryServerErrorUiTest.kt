package com.example.englishrussianflashcards.tests.features.cardcreation

import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest


private const val IMAGE_REPOSITORY_SERVER_ERROR_DIALOG_MESSAGE = "Image database error"

/**
 * Created by Igor Aghibalov on 11.10.2026
 */
@HiltAndroidTest
class ImageRepositoryServerErrorUiTest: BaseImageRepositoryErrorUiTest() {


    @BindValue
    @JvmField
    val serverErrorImageRepository = ServerErrorImageRepository()


    override fun setUp() {
        super.setUp()
        errorDialogMessage = IMAGE_REPOSITORY_SERVER_ERROR_DIALOG_MESSAGE
    }
}