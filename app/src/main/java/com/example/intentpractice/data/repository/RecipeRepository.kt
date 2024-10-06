package com.example.intentpractice.data.repository

import com.example.intentpractice.data.api.Endpoint
import com.example.intentpractice.data.dao.RecipeDao
import com.example.intentpractice.data.model.Recipe
import com.example.intentpractice.utils.NetworkUtils
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeRepository(private val recipeDao: RecipeDao) {

    private val retrofitClient = NetworkUtils.getRetrofitInstance("https://api-receitas-at4n.onrender.com")
    private val endpoint = retrofitClient.create(Endpoint::class.java)

    suspend fun insert(recipe: Recipe) {
        if (recipe.id == 0) {
            val newId = generateNewId()
            val receitaComNovoId = recipe.copy(id = newId)
            recipeDao.insert(receitaComNovoId)
        } else {
            // Se a receita já tem um ID (como as vindas da API), insira diretamente
            recipeDao.insert(recipe)
        }
    }

    suspend fun insertFromApi(recipe: Recipe) {
        val existingId = recipeDao.getById(recipe.id)
        if (existingId == null) {
            recipeDao.insert(recipe)
        } else {
            val newId = generateNewId()
            val receitaComNovoId = recipe.copy(id = newId)
            recipeDao.insert(receitaComNovoId)
        }
    }

    suspend fun getAllReceitas(): List<Recipe> {
        return recipeDao.getAllReceitas() // Esse método agora existe
    }

    suspend fun deleteAll() {
        recipeDao.deleteAll()
    }

    suspend fun generateNewId(): Int {
        val maxId = recipeDao.getMaxId() // Implementar no DAO
        return (maxId ?: 0) + 1 // Incrementa o maior ID encontrado
    }

    fun getReceitasFromApi(onSuccess: (List<Recipe>) -> Unit, onError: (String) -> Unit) {
        endpoint.getReceitas().enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                if (response.isSuccessful) {
                    response.body()?.let { jsonArray ->
                        val gson = Gson()
                        val receitaType = object : TypeToken<List<Recipe>>() {}.type
                        val newReceitasModels: List<Recipe> = gson.fromJson(jsonArray, receitaType)

                        CoroutineScope(Dispatchers.IO).launch {
                            newReceitasModels.forEach { receita ->
                                recipeDao.insert(receita)
                            }
                            println("Receitas inseridas: ${newReceitasModels.size}")

                            withContext(Dispatchers.Main) {
                                onSuccess(newReceitasModels)
                            }
                        }
                    } ?: onError("Resposta do servidor vazia")
                } else {
                    onError("Erro na resposta: ${response.code()}")
                }
            }


            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                onError("Erro na requisição: ${t.message}")
            }
        })
    }
}
