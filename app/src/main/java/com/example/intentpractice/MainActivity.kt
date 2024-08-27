package com.example.intentpractice

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val email = findViewById<EditText>(R.id.emailAddress)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.loginButton)
        val signin = findViewById<TextView>(R.id.signButton)
        val alert = findViewById<TextView>(R.id.alertMessage)

        val intent = intent
        val emailFromSignIn = intent.getStringExtra("email")
        val passwordFromSignIn = intent.getStringExtra("password")

        if (emailFromSignIn != null && passwordFromSignIn != null) {
            email.setText(emailFromSignIn)
            password.setText(passwordFromSignIn)
        }

        login.setOnClickListener {
            val emailText = email.text.toString().trim()
            val passwordText = password.text.toString().trim()

            if (emailText.isNotEmpty() && passwordText.isNotEmpty()) {
                val intent = Intent(this, MainMenu::class.java)
                intent.putExtra("email", "vindo da página inicial")
                startActivity(intent)
            } else {
                alert.visibility = View.VISIBLE
            }
        }

        signin.setOnClickListener {
            val intent2 = Intent(this, SignIn::class.java)
            intent2.putExtra("extra", "vindo da página inicial")
            startActivity(intent2)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signOutButton)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}