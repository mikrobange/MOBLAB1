package com.example.myapplicationasdfasdfasdfasdfasdf.SIGNALS

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/*
class MatavimasDatabase {
}*/



@Database(entities = [Matavimas::class], version = 4, exportSchema = false)
abstract class MatavimasDatabase : RoomDatabase() {
    abstract fun MatavimasDao(): MatavimasDao

    companion object {
        @Volatile
        private var INSTANCE: MatavimasDatabase? = null

        fun getDatabase(context: Context): MatavimasDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MatavimasDatabase::class.java,
                    "dab"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
@Database(entities = [Matavimas::class], version = 4, exportSchema = false)
abstract class MDatabase : RoomDatabase() {
    abstract fun MatavimasDao(): MatavimasDao

    companion object {
        @Volatile
        private var INSTANCE: MatavimasDatabase? = null

        fun getDatabase(context: Context): MatavimasDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MatavimasDatabase::class.java,
                    "dab"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}