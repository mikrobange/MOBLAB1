package com.example.myapplicationasdfasdfasdfasdfasdf.SIGNALS

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MatavimasDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(mats: Matavimas)

    @Update
    suspend fun update(mats: Matavimas)

    @Delete
    fun delete(mats: Matavimas)

    @Query("SELECT * from matavimai WHERE matavimas = :id")
    fun getSignals(id: Int): Flow<Matavimas>

    @Query("SELECT * FROM matavimai WHERE matavimas IN (:ids)")
    fun GETBOBDATA(ids: List<Int>): Flow<List<Matavimas>>


    @Query("SELECT * from matavimai ORDER BY matavimas ASC")
    fun getAllSignals(): Flow<List<Matavimas>>

    @Query("DELETE FROM matavimai")
    suspend fun deleteAllSignals()
}