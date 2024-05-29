package com.example.englishrussianflashcards.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * Created by Igor Aghibalov on 29.05.2024
 */
abstract class ObservableContainer <T: Any> (private val mutableLiveData: MutableLiveData<T>) {
    abstract fun setObserver(lifecycleOwner: LifecycleOwner, observer: Observer<T>)
    abstract fun getValue(): T
    abstract fun setValue(newValue: T)
}