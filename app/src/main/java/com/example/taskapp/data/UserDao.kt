package com.example.taskapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.taskapp.models.Task

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table ORDER BY priority ASC")
    fun readAllData(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(task: Task)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateUser(task: Task)

    @Delete
    suspend fun deleteUser(task: Task)

    @Query("DELETE FROM user_table WHERE id = :id")
    suspend fun deleteUserById(id: Int)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()
}