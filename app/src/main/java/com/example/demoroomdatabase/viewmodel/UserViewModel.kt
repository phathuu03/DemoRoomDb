package com.example.demoroomdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.room.Room
import com.example.demoroomdatabase.db.AppDatabase
import com.example.demoroomdatabase.entity.User
import kotlinx.coroutines.withContext

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val db =
        Room.databaseBuilder(application, AppDatabase::class.java, "database_name").build()
    private val userDao = db.userDao()
    private val _users = userDao.getAll()
    val users get() = _users


    fun delete(user: User) {
      viewModelScope.launch{
          withContext(Dispatchers.IO){
              userDao.delete(user)
          }
      }
    }
    fun clearData(){
        viewModelScope.launch {
            userDao.clearAll()
        }
    }

    fun insert(user: User) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                userDao.insertAll(user)
            }
        }
    }

    fun update(newUser : User){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                userDao.update(newUser)
            }
        }
    }




}