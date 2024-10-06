package com.example.intentpractice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intentpractice.data.model.Recipe
import com.example.intentpractice.data.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeViewModel(private val receitaRepository: RecipeRepository) : ViewModel() {
    private val _receitasModels = mutableListOf<Recipe>()
    val receitasModels: List<Recipe> get() = _receitasModels

    fun fetchReceitas(onDataFetched: () -> Unit) {
        receitaRepository.getReceitasFromApi(
            onSuccess = { newReceitas ->
                viewModelScope.launch {
                    // Primeiro, exclui todas as receitas do banco de dados
                    receitaRepository.deleteAll()

                    // Agora insere as novas receitas da API
                    newReceitas.forEach { receita ->
                        receitaRepository.insertFromApi(receita)
                    }

                    // Atualiza a lista carregando diretamente do banco de dados
                    _receitasModels.clear()
                    _receitasModels.addAll(receitaRepository.getAllReceitas())

                    // Notifica que os dados foram carregados e estão prontos para exibição
                    onDataFetched()
                }
            },
            onError = { errorMessage ->
                println("Erro ao buscar receitas: $errorMessage")
            }
        )
    }
}
