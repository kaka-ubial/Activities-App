package com.example.intentpractice.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.intentpractice.database.AppDatabase
import com.example.intentpractice.repository.UserRepository
import kotlinx.coroutines.launch
import com.example.intentpractice.model.User

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
