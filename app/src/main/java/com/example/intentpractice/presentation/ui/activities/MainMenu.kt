package com.example.intentpractice.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.intentpractice.presentation.viewmodel.MainMenuViewModel
import com.example.intentpractice.databinding.ActivityMainMenuBinding

class MainMenu : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding
    private val mainMenuViewModel: MainMenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ListRecipesButton.setOnClickListener {
            val intent2 = Intent(this, RecipeList::class.java)
            intent2.putExtra("extra", "vindo do menu principal")
            startActivity(intent2)
        }

        binding.profile.setOnClickListener {
            val profileIntent = Intent(this, UserProfile::class.java)
            startActivity(profileIntent)
        }

        binding.signOut.setOnClickListener {
            mainMenuViewModel.performLogout(this)
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }
}
