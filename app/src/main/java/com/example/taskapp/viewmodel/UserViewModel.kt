package com.example.taskapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taskapp.data.UserDatabase
import com.example.taskapp.repository.UserRepository
import com.example.taskapp.models.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Task>>
    private val repository: UserRepository

    init{
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(task)
        }
    }

    fun updateUser(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(task)
        }
    }

    fun deleteUser(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(task)
        }
    }

    fun deleteAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUsers()
        }
    }
}
