package com.example.intentpractice.ViewModel

import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$"
        return password.matches(Regex(passwordPattern))
    }
}
