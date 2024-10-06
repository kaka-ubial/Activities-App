package com.example.intentpractice.data.api

import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {
    @GET("receitas/todas")
    fun getReceitas() : Call<JsonArray>
}