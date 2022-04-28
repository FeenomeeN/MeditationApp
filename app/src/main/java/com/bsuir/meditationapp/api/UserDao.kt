package com.bsuir.meditationapp.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bsuir.meditationapp.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user WHERE is_authenticated = 1 LIMIT 1")
    suspend fun getAuthenticatedUser(): User

    @Query("SELECT * FROM user ORDER BY id ASC")
    suspend fun getAllUsers(): List<User>

    @Query("UPDATE user SET is_authenticated = 0")
    suspend fun deauthAllUsers()

}