package com.example.englishrussianflashcards.domain

import androidx.room.RoomDatabase


abstract class ApplicationDatabase: RoomDatabase(), DataSource {
    abstract fun getCardDao(): CardDao
    abstract fun getDictionaryDao(): DictionaryDao

}
