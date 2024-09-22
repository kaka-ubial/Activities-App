package com.example.intentpractice

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.intentpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val emailFromSignIn = intent.getStringExtra("email")
        val passwordFromSignIn = intent.getStringExtra("password")

        if (emailFromSignIn != null && passwordFromSignIn != null) {
            binding.emailAddress.setText(emailFromSignIn)
            binding.password.setText(passwordFromSignIn)
        }

        binding.loginButton.setOnClickListener {
            val emailText = binding.emailAddress.text.toString().trim()
            val passwordText = binding.password.text.toString().trim()

            if (emailText.isNotEmpty() && passwordText.isNotEmpty()) {
                val intent = Intent(this, MainMenu::class.java)
                intent.putExtra("email", "vindo da página inicial")
                startActivity(intent)
            } else {
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
