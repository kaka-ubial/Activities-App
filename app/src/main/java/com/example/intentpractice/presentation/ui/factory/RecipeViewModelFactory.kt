package com.example.intentpractice.presentation.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.intentpractice.data.repository.RecipeRepository
import com.example.intentpractice.presentation.viewmodel.RecipeViewModel

class RecipeViewModelFactory(private val receitaRepository: RecipeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecipeViewModel(receitaRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
