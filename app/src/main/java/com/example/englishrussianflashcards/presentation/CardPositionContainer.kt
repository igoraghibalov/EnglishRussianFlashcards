package com.example.englishrussianflashcards.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * Created by Igor Aghibalov on 01.06.2024
 */
class CardPositionContainer(cardPositionLiveData: MutableLiveData<Int>): ObservableContainer<Int>(cardPositionLiveData) {


    override fun setObserver(lifecycleOwner: LifecycleOwner, observer: Observer<Int>) {
        mutableLiveData.observe(lifecycleOwner, observer)
    }

    override fun getValue(): Int = mutableLiveData.value!!


    override fun setValue(newValue: Int) {
        mutableLiveData.value = newValue
    }
}