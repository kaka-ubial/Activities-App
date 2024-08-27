package com.example.intentpractice

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainMenu : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val signOut = findViewById<Button>(R.id.button)
        val cat1 = findViewById<ImageView>(R.id.cat1)
        val cat2 = findViewById<ImageView>(R.id.cat2)
        val cat3 = findViewById<ImageView>(R.id.cat3)
        val dog1 = findViewById<ImageView>(R.id.dog1)
        val dog2 = findViewById<ImageView>(R.id.dog2)
        val dog3 = findViewById<ImageView>(R.id.dog3)
        val bird1 = findViewById<ImageView>(R.id.bird1)
        val bird2 = findViewById<ImageView>(R.id.bird2)
        val bird3 = findViewById<ImageView>(R.id.bird3)
        val spinner: Spinner = findViewById(R.id.category)

        ArrayAdapter.createFromResource(
            this,
            R.array.animals_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                cat1.visibility = ImageView.GONE
                cat2.visibility = ImageView.GONE
                cat3.visibility = ImageView.GONE
                dog1.visibility = ImageView.GONE
                dog2.visibility = ImageView.GONE
                dog3.visibility = ImageView.GONE
                bird1.visibility = ImageView.GONE
                bird2.visibility = ImageView.GONE
                bird3.visibility = ImageView.GONE
                when (position) {
                    1 -> {
                        cat1.visibility = ImageView.VISIBLE
                        cat2.visibility = ImageView.VISIBLE
                        cat3.visibility = ImageView.VISIBLE
                    }
                    2 -> {
                        dog1.visibility = ImageView.VISIBLE
                        dog2.visibility = ImageView.VISIBLE
                        dog3.visibility = ImageView.VISIBLE
                    }
                    3 -> {
                        bird1.visibility = ImageView.VISIBLE
                        bird2.visibility = ImageView.VISIBLE
                        bird3.visibility = ImageView.VISIBLE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No action needed
            }
        }

        signOut.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signOutButton)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
