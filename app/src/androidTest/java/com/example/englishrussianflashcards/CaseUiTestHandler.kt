package com.example.englishrussianflashcards

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.example.englishrussianflashcards.testers.InflationOnClickTester

/**
 * Created by Igor Aghibalov on 16.06.2024
 */
abstract class CaseUiTestHandler: InflationOnClickTester(),
    CaseTestHandler,
                                  ScreenRotator {

    override fun rotateScreen() {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).setOrientationLeft()
    }
}