package com.example.intentpractice

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.intentpractice.databinding.ActivitySignInBinding

class SignIn : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cancelText.setOnClickListener {
            val leave = Intent(this, MainActivity::class.java)
            startActivity(leave)
        }

        binding.createAccountButton.setOnClickListener {
            val emailText = binding.emailsignIn.text.toString().trim()
            val passwordText1 = binding.password1.text.toString().trim()
            val passwordText2 = binding.password2.text.toString().trim()

            if (emailText.isNotEmpty() && passwordText1.isNotEmpty() && passwordText2.isNotEmpty()) {
                if (passwordText1 == passwordText2) {
                    val createAcc = Intent(this, MainActivity::class.java)
                    createAcc.putExtra("email", emailText)
                    createAcc.putExtra("password", passwordText1)
                    startActivity(createAcc)
                } else {
                    binding.alert2.text = "As senhas não coincidem"
                    binding.alert2.visibility = View.VISIBLE
                }
            } else {
                binding.alert2.visibility = View.VISIBLE
            }
        }
    }
}
