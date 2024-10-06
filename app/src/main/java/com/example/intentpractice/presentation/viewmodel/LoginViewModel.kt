package com.example.intentpractice.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.intentpractice.data.database.AppDatabase
import com.example.intentpractice.data.repository.UserRepository
import com.example.intentpractice.data.model.User

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        userRepository = UserRepository(userDao)
    }

    suspend fun getUserByEmailAndPassword(email: String, password: String): User? {
        return userRepository.getUserByEmailAndPassword(email, password)
    }

    suspend fun isUserValid(email: String, password: String): Boolean {
        val user = userRepository.getUserByEmail(email)
        return user != null && user.password == password
    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$"
        return password.matches(Regex(passwordPattern))
    }
}
