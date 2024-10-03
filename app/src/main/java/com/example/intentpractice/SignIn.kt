package com.example.intentpractice

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.intentpractice.ViewModel.SignInViewModel
import com.example.intentpractice.databinding.ActivitySignInBinding

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
            val emailText = binding.emailsignIn.text.toString().trim()
            val passwordText1 = binding.password1.text.toString().trim()
            val passwordText2 = binding.password2.text.toString().trim()

            binding.alert2.visibility = View.GONE

            if (emailText.isNotEmpty() && passwordText1.isNotEmpty() && passwordText2.isNotEmpty()) {
                if (!signInViewModel.isEmailValid(emailText)) {
                    binding.alert2.text = "Email inválido"
                    binding.alert2.visibility = View.VISIBLE
                } else if (!signInViewModel.isPasswordValid(passwordText1)) {
                    binding.alert2.text = "A senha deve ter no mínimo 8 caracteres, uma letra maiúscula e um número"
                    binding.alert2.visibility = View.VISIBLE
                } else if (passwordText1 != passwordText2) {
                    binding.alert2.text = "As senhas não coincidem"
                    binding.alert2.visibility = View.VISIBLE
                } else {
                    // Tudo válido, prosseguir com a criação da conta
                    val createAcc = Intent(this, Login::class.java)
                    createAcc.putExtra("email", emailText)
                    createAcc.putExtra("password", passwordText1)
                    startActivity(createAcc)
                }
            } else {
                binding.alert2.text = "Por favor, preencha todos os campos."
                binding.alert2.visibility = View.VISIBLE
            }
        }
    }
}
