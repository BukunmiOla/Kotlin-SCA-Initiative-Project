package com.scainitiative.kotlinscainitiativeproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDAO

    companion object{
        private var INSTANCE : AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase?{

            if (INSTANCE == null){
                INSTANCE= Room.databaseBuilder<AppDatabase>(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "note-database")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}