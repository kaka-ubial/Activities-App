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
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val recipeDao: RecipeDao,
) {
    private val retrofitClient = NetworkUtils.getRetrofitInstance("https://api-receitas-at4n.onrender.com")
    private val endpoint = retrofitClient.create(Endpoint::class.java)

    suspend fun insert(recipe: Recipe) {
        recipeDao.insert(recipe)
    }

    suspend fun insertAll(recipes: List<Recipe>) {
        recipes.forEach { recipe -> insert(recipe) }
    }

    suspend fun getAllReceitas(): List<Recipe> {
        return recipeDao.getAllReceitas()
    }

    suspend fun deleteAll() {
        recipeDao.deleteAll()
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
                            deleteAll()
                            insertAll(newReceitasModels)

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
