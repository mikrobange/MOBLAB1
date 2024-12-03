package com.example.myapplicationasdfasdfasdfasdfasdf.DATA

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class User(
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val mac: String,
    val sensorius: String,
    val stiprumas: String
)




