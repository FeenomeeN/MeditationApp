package com.bsuir.meditationapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bsuir.meditationapp.api.UserDao
import com.bsuir.meditationapp.model.User

@Database(version = 1, entities = [User::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}