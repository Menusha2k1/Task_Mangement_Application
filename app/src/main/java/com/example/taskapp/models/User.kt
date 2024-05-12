package com.example.taskapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "user_table")
class User (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val firstname:String,
    val lastName:String,
    val age:Int
)