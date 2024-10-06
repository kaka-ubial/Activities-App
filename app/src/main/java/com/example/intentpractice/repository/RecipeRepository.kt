package com.example.intentpractice.repository

import com.example.intentpractice.api.Endpoint
import com.google.gson.JsonArray
import retrofit2.Call
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val api: Endpoint
) {
   fun getRecipes(): Call<JsonArray> {
        return api.getRecipes()
    }
}
