package com.example.myapplicationasdfasdfasdfasdfasdf.SIGNALS

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matavimai")
data class Matavimas(
    @PrimaryKey (autoGenerate = true)
    val matavimas: Int = 0,
    val x: Int,
    val y: Int,
    val atstumas: Float
)