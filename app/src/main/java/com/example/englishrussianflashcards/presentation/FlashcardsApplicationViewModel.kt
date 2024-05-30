package com.example.englishrussianflashcards.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * Created by Igor Aghibalov on 29.05.2024
 */
abstract class FlashcardsApplicationViewModel<T: Any>(private val observableContainerMap: Map<String, ObservableContainer<T>>,
                                                      private val repository: Repository,
                                                      private val savedStateHandle: SavedStateHandle)
                         : ViewModel(), ObservableContainerSubscriber {
}