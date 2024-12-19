package com.example.demoroomdatabase.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.demoroomdatabase.dao.UserDao
import com.example.demoroomdatabase.entity.User

@Database(entities =[User::class], version =1)
 abstract class AppDatabase :RoomDatabase(){
    abstract fun userDao() : UserDao
}