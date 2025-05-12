package com.kirumbastacy.panoramahotel.repository

import com.kirumbastacy.panoramahotel.data.UserDao
import com.kirumbastacy.panoramahotel.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) {
        userDao.registerUser(user)
    }

    suspend fun loginUser(email: String, password: String): User? {
        return userDao.loginUser(email, password)
    }

    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }
}