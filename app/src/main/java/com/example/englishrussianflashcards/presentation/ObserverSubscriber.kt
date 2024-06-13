package com.example.englishrussianflashcards.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

/**
 * Created by Igor Aghibalov on 13.06.2024
 */
interface ObserverSubscriber {

    fun <T> subscribeObserverToValueChange(observableContainerTag: String,
                                           lifecycleOwner: LifecycleOwner,
                                           observer: Observer<out T>)
}