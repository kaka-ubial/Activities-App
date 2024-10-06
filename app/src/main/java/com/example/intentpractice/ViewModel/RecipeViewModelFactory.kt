package com.example.intentpractice.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.intentpractice.recipeActivity.RecipeViewModel
import com.example.intentpractice.repository.ReceitaRepository

class RecipeViewModelFactory(private val receitaRepository: ReceitaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecipeViewModel(receitaRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
