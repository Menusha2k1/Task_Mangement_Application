package com.example.taskapp.repository

import androidx.lifecycle.LiveData
import com.example.taskapp.data.UserDao
import com.example.taskapp.models.User

class UserRepository(private val userDao: UserDao) {

    val  readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}