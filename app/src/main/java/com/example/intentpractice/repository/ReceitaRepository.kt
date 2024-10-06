package com.example.intentpractice.repository

import com.example.intentpractice.api.Endpoint
import com.example.intentpractice.dao.ReceitaModelDao
import com.example.intentpractice.model.ReceitaModel
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

class ReceitaRepository(private val receitaModelDao: ReceitaModelDao) {

    private val retrofitClient = NetworkUtils.getRetrofitInstance("https://api-receitas-at4n.onrender.com")
    private val endpoint = retrofitClient.create(Endpoint::class.java)

    suspend fun insert(receitaModel: ReceitaModel) {
        if (receitaModel.id == 0) {
            val newId = generateNewId()
            val receitaComNovoId = receitaModel.copy(id = newId)
            receitaModelDao.insert(receitaComNovoId)
        } else {
            // Se a receita já tem um ID (como as vindas da API), insira diretamente
            receitaModelDao.insert(receitaModel)
        }
    }

    suspend fun insertFromApi(receitaModel: ReceitaModel) {
        val existingId = receitaModelDao.getById(receitaModel.id)
        if (existingId == null) {
            receitaModelDao.insert(receitaModel)
        } else {
            val newId = generateNewId()
            val receitaComNovoId = receitaModel.copy(id = newId)
            receitaModelDao.insert(receitaComNovoId)
        }
    }

    suspend fun getAllReceitas(): List<ReceitaModel> {
        return receitaModelDao.getAllReceitas() // Esse método agora existe
    }

    suspend fun deleteAll() {
        receitaModelDao.deleteAll()
    }

    suspend fun generateNewId(): Int {
        val maxId = receitaModelDao.getMaxId() // Implementar no DAO
        return (maxId ?: 0) + 1 // Incrementa o maior ID encontrado
    }

    fun getReceitasFromApi(onSuccess: (List<ReceitaModel>) -> Unit, onError: (String) -> Unit) {
        endpoint.getReceitas().enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                if (response.isSuccessful) {
                    response.body()?.let { jsonArray ->
                        val gson = Gson()
                        val receitaType = object : TypeToken<List<ReceitaModel>>() {}.type
                        val newReceitasModels: List<ReceitaModel> = gson.fromJson(jsonArray, receitaType)

                        CoroutineScope(Dispatchers.IO).launch {
                            newReceitasModels.forEach { receita ->
                                receitaModelDao.insert(receita)
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
