package com.example.englishrussianflashcards.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.englishrussianflashcards.R

class MainActivity : AppCompatActivity(R.layout.main_activity_layout) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainMenuFragmentTag = "MainMenuFragment"
        val mainMenuFragment = supportFragmentManager.findFragmentByTag(mainMenuFragmentTag)

        if (mainMenuFragment == null) {
            supportFragmentManager.commit {
                replace<MainMenuFragment>(R.id.fragment_container_view, tag = mainMenuFragmentTag)
                setReorderingAllowed(true)
                addToBackStack("MainMenuFragmentAppendingTransaction")
            }
        }
    }
}