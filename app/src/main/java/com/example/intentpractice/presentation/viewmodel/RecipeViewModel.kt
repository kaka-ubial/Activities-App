package com.example.intentpractice.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intentpractice.data.model.Recipe
import com.example.intentpractice.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val receitaRepository: RecipeRepository
) : ViewModel() {
    private val _receitasModels = MutableLiveData<List<Recipe>>()
    val receitasModels: LiveData<List<Recipe>> get() = _receitasModels

    fun fetchReceitas(onDataFetched: () -> Unit) {
        receitaRepository.getReceitasFromApi(
            onSuccess = { newReceitas ->
                viewModelScope.launch {
                    receitaRepository.insertAll(newReceitas)

                    _receitasModels.postValue(receitaRepository.getAllReceitas())

                    onDataFetched()
                }
            },
            onError = { errorMessage ->
                println("Erro ao buscar receitas: $errorMessage")
            }
        )
    }

    fun insertRecipe(recipe: Recipe) {
        viewModelScope.launch {
            receitaRepository.insert(recipe)
            _receitasModels.postValue(receitaRepository.getAllReceitas())
        }
    }
}
