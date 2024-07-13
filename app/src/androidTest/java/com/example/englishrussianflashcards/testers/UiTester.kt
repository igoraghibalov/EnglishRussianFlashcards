package com.example.englishrussianflashcards.testers

import android.app.Application
import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.englishrussianflashcards.Tester
import com.example.englishrussianflashcards.presentation.MainActivity
import org.junit.Before
import org.junit.runner.RunWith

/**
 * Created by Igor Aghibalov on 22.04.2024
 */
@RunWith(AndroidJUnit4::class)
abstract class UiTester: Tester() {

    protected lateinit var mainActivityScenario: ActivityScenario<MainActivity>
    protected lateinit var applicationContext: Context
    protected lateinit var inflationOnClickTester: InflationOnClickTester

    @Before
    override fun setupTestEnvironment() {
        applicationContext = ApplicationProvider.getApplicationContext<Application>()
        mainActivityScenario = ActivityScenario.launch(MainActivity::class.java)
        inflationOnClickTester = InflationOnClickTester()
    }
}