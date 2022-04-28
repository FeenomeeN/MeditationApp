package com.bsuir.meditationapp.repository

import com.bsuir.meditationapp.db.AppDatabase
import com.bsuir.meditationapp.model.User

class UserRepository(private val database: AppDatabase) : IUserRepository {

    override suspend fun addUser(user: User) = database.userDao.addUser(user)

    override suspend fun getAuthenticatedUser() = database.userDao.getAuthenticatedUser()

    override suspend fun getAllUsers() = database.userDao.getAllUsers()

    override suspend fun deauthAllUsers() = database.userDao.deauthAllUsers()

}

interface IUserRepository {

    suspend fun addUser(user: User)

    suspend fun getAuthenticatedUser(): User

    suspend fun getAllUsers(): List<User>

    suspend fun deauthAllUsers()

}