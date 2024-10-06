package com.example.intentpractice.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "receita_table")
data class Recipe (
    @PrimaryKey val id: Int,
    val receita: String?,
    val ingredientes: String?,
    val modo_de_preparo: String?,
    val link_imagem: String?,
    val tipo: String?,
    val created_at: String?,
    val ingredientesBaseJson: String?
) {
    fun getIngredientesBase(): List<BaseIngredients> {
        val gson = Gson()
        val listType = object : TypeToken<List<BaseIngredients>>() {}.type
        return gson.fromJson(ingredientesBaseJson, listType)
    }
}
