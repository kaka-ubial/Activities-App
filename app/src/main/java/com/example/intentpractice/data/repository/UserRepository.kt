package com.example.intentpractice.data.repository

import com.example.intentpractice.data.dao.UserDao
import com.example.intentpractice.data.model.User

class UserRepository(private val userDao: UserDao) {

    suspend fun insert(user: User): Long {
        return userDao.insert(user)
    }

    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    suspend fun getUserByEmailAndPassword(email: String, password: String): User? {
        return userDao.getUserByEmailAndPassword(email, password)
    }

    suspend fun getUserById(userId: Int): User? {
        return userDao.getUserById(userId)
    }

    suspend fun deleteUserAccount(email: String): Boolean {
        val rowsDeleted = userDao.deleteUserAccount(email)
        return rowsDeleted > 0
    }

    suspend fun updatePassword(userId: Int, newPassword: String): Int {
        return userDao.updatePassword(userId, newPassword)
    }

    suspend fun updateName(userId: Int, newName: String): Int {
        return userDao.updateName(userId, newName)
    }



    suspend fun updateEmail(userId: Int, newEmail: String): Int {
        return userDao.updateEmail(userId, newEmail)
    }
}
