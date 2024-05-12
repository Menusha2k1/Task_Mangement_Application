package com.example.taskapp.repository

import androidx.lifecycle.LiveData
import com.example.taskapp.data.UserDao
import com.example.taskapp.models.Task

class UserRepository(private val userDao: UserDao) {

    val  readAllData: LiveData<List<Task>> = userDao.readAllData()

    suspend fun addUser(task: Task){
        userDao.addUser(task)
    }
    suspend fun updateUser(task: Task){
        userDao.updateUser(task)
    }

    suspend fun deleteUser(task: Task){
        userDao.deleteUser(task)
    }

    suspend fun deleteUserById(id: Int){
        userDao.deleteUserById(id)
    }
    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }
}