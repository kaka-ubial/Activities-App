package com.example.intentpractice.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.intentpractice.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM user_table WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM user_table WHERE email = :email AND password = :password LIMIT 1")
    suspend fun getUserByEmailAndPassword(email: String, password: String): User?

    @Query("SELECT * FROM user_table WHERE id = :userId LIMIT 1")
    suspend fun getUserById(userId: Int): User?

    @Query("DELETE FROM user_table WHERE email = :email")
    suspend fun deleteUserAccount(email: String): Int

    @Query("UPDATE user_table SET password = :newPassword WHERE id = :userId")
    suspend fun updatePassword(userId: Int, newPassword: String): Int

    @Query("UPDATE user_table SET name = :newName WHERE id = :userId")
    suspend fun updateName(userId: Int, newName: String): Int

    @Query("UPDATE user_table SET email = :newEmail WHERE id = :userId")
    suspend fun updateEmail(userId: Int, newEmail: String): Int
}
