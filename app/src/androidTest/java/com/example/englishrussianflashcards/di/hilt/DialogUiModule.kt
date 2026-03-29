package com.example.englishrussianflashcards.di.hilt

import com.example.englishrussianflashcards.screenuielements.DialogUi
import com.example.englishrussianflashcards.screenuielements.espresso.EspressoAlertDialogUi
import dagger.Module
import dagger.Provides


private const val IMAGE_API_SERVER_ERROR_DIALOG_MESSAGE = "Image server error"
private const val NETWORK_CONNECTION_ERROR_DIALOG_MESSAGE = "No Internet connection"
private const val ALERT_DIALOG_DISMISS_BUTTON_TEXT = "ok"

/**
 * Created by Igor Aghibalov on 29.03.2026
 */
@Module
object DialogUiModule {

    @EspressoImageApiServerErrorDialogUi
    @Provides
    fun provideImageApiServerErrorDialogUi(): DialogUi {

        return EspressoAlertDialogUi(dialogMessage = IMAGE_API_SERVER_ERROR_DIALOG_MESSAGE,
                                     dismissButtonText = ALERT_DIALOG_DISMISS_BUTTON_TEXT)
    }


    @EspressoNetworkConnectionErrorDialogUi
    @Provides
    fun provideNetworkConnectionErrorDialogUi(): DialogUi {

        return EspressoAlertDialogUi(dialogMessage = NETWORK_CONNECTION_ERROR_DIALOG_MESSAGE,
                                     dismissButtonText = ALERT_DIALOG_DISMISS_BUTTON_TEXT)
    }
}