package com.example.englishrussianflashcards.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.englishrussianflashcards.domain.Repository

/**
 * Created by Igor Aghibalov on 29.05.2024
 */
abstract class FlashcardsApplicationViewModel<T: Any>(protected val observableContainer: ObservableContainer<out T>,
                                                      protected val repository: Repository,
                                                      protected val savedStateHandle: SavedStateHandle = SavedStateHandle())
                         : ViewModel() {
}