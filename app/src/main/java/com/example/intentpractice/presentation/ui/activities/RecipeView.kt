package com.example.intentpractice.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.intentpractice.R
import com.example.intentpractice.databinding.ActivityRecipeViewBinding
import com.squareup.picasso.Picasso

class RecipeView : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goBackButton.setOnClickListener {
            startActivity(Intent(this, MainMenu::class.java))
        }

        val title = intent.getStringExtra("RECIPE_TITLE")
        val ingredients = intent.getStringExtra("RECIPE_INGREDIENTS")
        val method = intent.getStringExtra("RECIPE_METHOD")
        val imageUrl = intent.getStringExtra("RECIPE_IMAGE")

        val titleTextView: TextView = findViewById(R.id.recipeTitleTextView)
        val ingredientsTextView: TextView = findViewById(R.id.recipeIngredientsListTextView)
        val methodTextView: TextView = findViewById(R.id.recipeMethodStepsTextView)
        val imageView: ImageView = findViewById(R.id.recipeImageView)

        titleTextView.text = title
        ingredientsTextView.text = ingredients
        methodTextView.text = method

        println(imageUrl)
        Picasso.get().load(imageUrl).into(imageView)

    }
}