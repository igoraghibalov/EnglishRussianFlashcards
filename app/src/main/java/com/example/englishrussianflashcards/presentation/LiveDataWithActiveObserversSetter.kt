package com.example.englishrussianflashcards.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Created by Igor Aghibalov on 29.05.2024
 */
interface LiveDataWithActiveObserversSetter<T> {

    fun setLiveDataWithActiveObservers(liveData: LiveData<T>)
}