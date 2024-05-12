package com.example.taskapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskapp.models.Task

@Database(entities = [Task::class], version = 2, exportSchema = false)
abstract class UserDatabase: RoomDatabase(){

    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE :UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase{
            val tempInsrance = INSTANCE
            if(tempInsrance != null){
                return tempInsrance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "TaskifyTest"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}