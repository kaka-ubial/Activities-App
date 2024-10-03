package com.example.intentpractice.ViewModel
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^(?=.*[A-Z])(?=.*[0-9]).{8,}$"
        return password.matches(Regex(passwordPattern))
    }
}
