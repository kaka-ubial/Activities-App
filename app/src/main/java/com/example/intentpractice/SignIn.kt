package com.example.intentpractice

import android.annotation.SuppressLint
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

class SignIn : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        val email = findViewById<EditText>(R.id.emailsignIn)
        val password = findViewById<EditText>(R.id.password1)
        val password2 = findViewById<EditText>(R.id.password2)
        val cancel = findViewById<TextView>(R.id.cancelText)
        val save = findViewById<Button>(R.id.createAccountButton)
        val alert = findViewById<TextView>(R.id.alert2)

        cancel.setOnClickListener {
            val leave = Intent(this, MainActivity::class.java)
            startActivity(leave)
        }

        save.setOnClickListener {
            val emailText = email.text.toString().trim()
            val passwordText1 = password.text.toString().trim()
            val passwordText2 = password2.text.toString().trim()

            if (emailText.isNotEmpty() && passwordText1.isNotEmpty() && passwordText2.isNotEmpty()){
                if(passwordText1 == passwordText2){
                    val createAcc = Intent(this, MainActivity::class.java)
                    createAcc.putExtra("email", emailText)
                    createAcc.putExtra("password", passwordText1)
                    startActivity(createAcc)
                } else {
                    alert.text = "As senhas não coincidem"
                    alert.visibility = View.VISIBLE
                }
            } else {
                alert.visibility = View.VISIBLE
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signOutButton)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}