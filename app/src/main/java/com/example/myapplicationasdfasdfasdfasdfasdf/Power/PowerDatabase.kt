package com.example.myapplicationasdfasdfasdfasdfasdf.Power

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/*
class MatavimasDatabase {
}*/



@Database(entities = [Power::class], version = 1, exportSchema = false)
abstract class PowerDatabase : RoomDatabase() {
    abstract fun PowerDao(): PowerDao

    companion object {
        @Volatile
        private var INSTANCE: PowerDatabase? = null

        fun getDatabase(context: Context): PowerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PowerDatabase::class.java,
                    "bab"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}