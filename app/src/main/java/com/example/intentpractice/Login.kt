package com.example.intentpractice

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.intentpractice.ViewModel.LoginViewModel
import com.example.intentpractice.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import com.example.intentpractice.SignIn

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val emailFromSignIn = intent.getStringExtra("email")
        val passwordFromSignIn = intent.getStringExtra("password")

        if (emailFromSignIn != null && passwordFromSignIn != null) {
            binding.emailAddress.setText(emailFromSignIn)
            binding.password.setText(passwordFromSignIn)
        }

        binding.loginButton.setOnClickListener {
            val emailText = binding.emailAddress.text.toString().trim()
            val passwordText = binding.password.text.toString().trim()

            binding.alertMessage.visibility = View.GONE

            if (loginViewModel.isEmailValid(emailText) && loginViewModel.isPasswordValid(passwordText)) {
                lifecycleScope.launch {
                    val user = loginViewModel.getUserByEmailAndPassword(emailText, passwordText) // Altere para obter o usuário completo
                    if (user != null) {
                        // Armazena o ID do usuário logado
                        val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putInt("logged_user_id", user.id) // Aqui você armazena o ID do usuário
                        editor.apply()

                        val intent = Intent(this@Login, MainMenu::class.java)
                        intent.putExtra("email", emailText)
                        startActivity(intent)
                        finish() // Finaliza a LoginActivity
                    } else {
                        binding.alertMessage.text = "Credenciais inválidas. Tente novamente."
                        binding.alertMessage.visibility = View.VISIBLE
                    }
                }
            } else {
                if (!loginViewModel.isEmailValid(emailText)) {
                    binding.alertMessage.text = "Email inválido"
                } else if (!loginViewModel.isPasswordValid(passwordText)) {
                    binding.alertMessage.text = "A senha deve ter no mínimo 8 caracteres, uma letra maiúscula e um número"
                }
                binding.alertMessage.visibility = View.VISIBLE
            }
        }

        binding.signButton.setOnClickListener {
            val intent2 = Intent(this, SignIn::class.java)
            intent2.putExtra("extra", "vindo da página inicial")
            startActivity(intent2)
        }
    }
}
