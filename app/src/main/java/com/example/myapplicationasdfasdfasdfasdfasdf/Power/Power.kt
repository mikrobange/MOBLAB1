package com.example.myapplicationasdfasdfasdfasdfasdf.Power

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "powers")
data class Power(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    val matavimas: Int,
    val sensorius: String,
    val stiprumas: Int
)