package com.example.intentpractice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intentpractice.data.model.User
import com.example.intentpractice.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserProfileViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getUserByEmail(email: String, onResult: (User?) -> Unit) {
        viewModelScope.launch {
            val user = userRepository.getUserByEmail(email)
            onResult(user)
        }
    }

    fun getUserById(userId: Int, callback: (User?) -> Unit) {
        viewModelScope.launch {
            val user = userRepository.getUserById(userId)
            callback(user)
        }
    }

    suspend fun updateName(userId: Int, newName: String): Boolean {
        val rowsUpdated = userRepository.updateName(userId, newName)
        return rowsUpdated > 0
    }

    suspend fun updateEmail(userId: Int, newEmail: String): Boolean {
        val rowsUpdated = userRepository.updateEmail(userId, newEmail)
        return rowsUpdated > 0
    }

    suspend fun updatePassword(userId: Int, newPassword: String): Boolean {
        val rowsUpdated = userRepository.updatePassword(userId, newPassword)
        return rowsUpdated > 0
    }

    suspend fun deleteUserAccount(email: String): Boolean {
        val rowsDeleted = userRepository.deleteUserAccount(email)
        return rowsDeleted
    }
}
