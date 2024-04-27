package com.example.englishrussianflashcards

import android.os.Process
/**
 * Created by Igor Aghibalov on 27.04.2024
 */
class EnglishRussianFlashcardsApplicationProcessTerminator: ProcessTerminator {


    override fun terminateProcess() {
        Process.killProcess(Process.myPid())
    }
}