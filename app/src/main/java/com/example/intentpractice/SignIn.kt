package com.example.intentpractice

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.intentpractice.Login
import com.example.intentpractice.ViewModel.SignInViewModel
import com.example.intentpractice.databinding.ActivitySignInBinding
import kotlinx.coroutines.launch

class SignIn : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cancelText.setOnClickListener {
            val leave = Intent(this, Login::class.java)
            startActivity(leave)
        }

        binding.createAccountButton.setOnClickListener {
            val name = binding.username.text.toString().trim()
            val emailText = binding.emailsignIn.text.toString().trim()
            val passwordText1 = binding.password1.text.toString().trim()
            val passwordText2 = binding.password2.text.toString().trim()

            binding.alert2.visibility = View.GONE

            if (name.isNotEmpty() && emailText.isNotEmpty() && passwordText1.isNotEmpty() && passwordText2.isNotEmpty()) {
                when {
                    !signInViewModel.isEmailValid(emailText) -> {
                        binding.alert2.text = "Email inválido"
                        binding.alert2.visibility = View.VISIBLE
                    }
                    !signInViewModel.isPasswordValid(passwordText1) -> {
                        binding.alert2.text = "A senha deve ter no mínimo 8 caracteres, uma letra maiúscula e um número"
                        binding.alert2.visibility = View.VISIBLE
                    }
                    passwordText1 != passwordText2 -> {
                        binding.alert2.text = "As senhas não coincidem"
                        binding.alert2.visibility = View.VISIBLE
                    }
                    else -> {
                        lifecycleScope.launch {
                            val isUserCreated =
                                signInViewModel.createUser(name, emailText, passwordText1)
                            runOnUiThread {
                                if (isUserCreated) {
                                    val createAcc = Intent(this@SignIn, Login::class.java)
                                    createAcc.putExtra("email", emailText)
                                    createAcc.putExtra("password", passwordText1)
                                    startActivity(createAcc)
                                } else {
                                    binding.alert2.text = "Falha ao criar usuário."
                                    binding.alert2.visibility = View.VISIBLE
                                }
                            }
                        }
                    }  }
            } else {
                binding.alert2.text = "Por favor, preencha todos os campos."
                binding.alert2.visibility = View.VISIBLE
            }
        }
    }
}  