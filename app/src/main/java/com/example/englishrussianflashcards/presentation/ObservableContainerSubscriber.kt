package com.example.englishrussianflashcards.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

/**
 * Created by Igor Aghibalov on 29.05.2024
 */
interface ObservableContainerSubscriber {

    fun <T> subscribeObserverToContainerValueChange(observableContainerTag: String,
                                                    lifecycleOwner: LifecycleOwner,
                                                    observer: Observer<T>)
}