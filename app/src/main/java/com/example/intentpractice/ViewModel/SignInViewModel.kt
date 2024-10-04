package com.example.intentpractice.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intentpractice.database.AppDatabase
import com.example.intentpractice.model.User
import com.example.intentpractice.repository.UserRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SignInViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        userRepository = UserRepository(userDao)
    }

    suspend fun createUser(name: String, email: String, password: String): Boolean {
        val user = User(name = name, email = email, password = password)
        return try {
            userRepository.insert(user)
            true // Usuário criado com sucesso
        } catch (e: Exception) {
            e.printStackTrace() // Imprime o erro no logcat
            false // Falha na criação do usuário
        }
    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$"
        return password.matches(Regex(passwordPattern))
    }
}
