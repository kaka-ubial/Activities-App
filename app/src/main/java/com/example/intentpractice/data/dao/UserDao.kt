package com.example.intentpractice.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.intentpractice.data.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM usuario WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM usuario WHERE email = :email AND password = :password LIMIT 1")
    suspend fun getUserByEmailAndPassword(email: String, password: String): User?

    @Query("SELECT * FROM usuario WHERE id = :userId LIMIT 1")
    suspend fun getUserById(userId: Int): User?

    @Query("DELETE FROM usuario WHERE email = :email")
    suspend fun deleteUserAccount(email: String): Int

    @Query("UPDATE usuario SET password = :newPassword WHERE id = :userId")
    suspend fun updatePassword(userId: Int, newPassword: String): Int

    @Query("UPDATE usuario SET name = :newName WHERE id = :userId")
    suspend fun updateName(userId: Int, newName: String): Int

    @Query("UPDATE usuario SET email = :newEmail WHERE id = :userId")
    suspend fun updateEmail(userId: Int, newEmail: String): Int
}
