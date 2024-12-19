package com.example.demoroomdatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    var name:String,
    var age:Int
)


