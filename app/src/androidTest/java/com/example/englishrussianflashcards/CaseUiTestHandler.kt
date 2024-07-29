package com.example.englishrussianflashcards

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.example.englishrussianflashcards.testers.BaseInflationOnClickTester

/**
 * Created by Igor Aghibalov on 16.06.2024
 */
abstract class CaseUiTestHandler: BaseInflationOnClickTester(),
    CaseTestHandler,
                                  ScreenRotator {

    override fun rotateScreen() {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).setOrientationLeft()
    }
}