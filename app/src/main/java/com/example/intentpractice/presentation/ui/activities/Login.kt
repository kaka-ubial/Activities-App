package com.example.intentpractice.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.intentpractice.presentation.viewmodel.LoginViewModel
import com.example.intentpractice.databinding.ActivityMainBinding
import com.example.intentpractice.utils.LoadingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Login : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        intent.getStringExtra("email")?.let { binding.emailAddress.setText(it) }
        intent.getStringExtra("password")?.let { binding.password.setText(it) }

        binding.loginButton.setOnClickListener {
            val emailText = binding.emailAddress.text.toString().trim()
            val passwordText = binding.password.text.toString().trim()

            binding.alertMessage.visibility = View.GONE

            if (loginViewModel.isEmailValid(emailText) && loginViewModel.isPasswordValid(passwordText)) {

                lifecycleScope.launch {
                    val user = loginViewModel.getUserByEmailAndPassword(emailText, passwordText)

                    if (user != null) {
                        val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
                        sharedPreferences.edit().putInt("logged_user_id", user.id).apply()

                        delay(2000) // Simulação de atraso antes de iniciar a próxima atividade

                        val intent = Intent(this@Login, LoadingActivity::class.java).apply {
                            putExtra("email", emailText)
                        }
                        startActivity(intent)
                        finish()
                    } else {
                        binding.alertMessage.text = "Credenciais inválidas. Tente novamente."
                        binding.alertMessage.visibility = View.VISIBLE
                    }
                }
            } else {
                binding.alertMessage.text = if (!loginViewModel.isEmailValid(emailText)) {
                    "Email inválido"
                } else {
                    "A senha deve ter no mínimo 8 caracteres, uma letra maiúscula e um número"
                }
                binding.alertMessage.visibility = View.VISIBLE
            }
        }

        binding.signButton.setOnClickListener {
            startActivity(Intent(this, SignIn::class.java).apply {
                putExtra("extra", "vindo da página inicial")
            })
        }
    }
}
