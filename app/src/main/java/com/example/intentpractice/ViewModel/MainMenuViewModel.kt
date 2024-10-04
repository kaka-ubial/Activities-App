package com.example.intentpractice.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel

class MainMenuViewModel : ViewModel() {

    fun performLogout(context: Context) {
        // Remove o ID do usuário dos SharedPreferences
        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("logged_user_id") // Remove o ID do usuário logado
        editor.apply()
    }
}
