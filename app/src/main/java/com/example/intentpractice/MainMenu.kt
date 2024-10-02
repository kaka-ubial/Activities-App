package com.example.intentpractice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.intentpractice.databinding.ActivityMainMenuBinding
import com.example.intentpractice.recipeActivity.RecipeListActivity

class MainMenu : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.ListRecipesButton.setOnClickListener {
            val intent2 = Intent(this, RecipeListActivity::class.java)
            intent2.putExtra("extra", "vindo do menu principal")
            startActivity(intent2)
        }
    }
}
