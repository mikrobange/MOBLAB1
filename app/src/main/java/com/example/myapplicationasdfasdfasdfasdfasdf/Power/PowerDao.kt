package com.example.myapplicationasdfasdfasdfasdfasdf.Power

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PowerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(mats: Power)

    @Update
    suspend fun update(mats: Power)

    @Delete
    fun delete(mats: Power)

    @Query("SELECT * from powers WHERE id = :id")
    fun getPowers(id: Int): Flow<Power>

    @Query("SELECT * from powers ORDER BY id ASC")
    fun GetAll(): Flow<List<Power>>

}