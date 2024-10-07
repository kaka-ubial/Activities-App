package com.example.intentpractice.presentation.ui.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.intentpractice.R

class RecipeView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_view)

        val title = intent.getStringExtra("RECIPE_TITLE")
        val ingredients = intent.getStringExtra("RECIPE_INGREDIENTS")
        val method = intent.getStringExtra("RECIPE_METHOD")
        val imageUrl = intent.getStringExtra("RECIPE_IMAGE")

        // Exibir os dados na tela
        val titleTextView: TextView = findViewById(R.id.recipeTitleTextView)
        val ingredientsTextView: TextView = findViewById(R.id.recipeIngredientsListTextView)
        val methodTextView: TextView = findViewById(R.id.recipeMethodStepsTextView)
        val imageView: ImageView = findViewById(R.id.recipeImageView)

        titleTextView.text = title
        ingredientsTextView.text = ingredients
        methodTextView.text = method

    }
}