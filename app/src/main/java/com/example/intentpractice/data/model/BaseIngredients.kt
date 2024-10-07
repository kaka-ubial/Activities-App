package com.example.intentpractice.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "ingredientesbase")
data class BaseIngredients (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome_ingredienteJson: String, // Campo para armazenar a lista convertida em JSON
    val receitaId: String?,
    val created_at: String?
) {
    fun getNomesIngrediente(): List<IngredientNames> {
        val gson = Gson()
        val listType = object : TypeToken<List<IngredientNames>>() {}.type
        return gson.fromJson(nome_ingredienteJson, listType)
    }
}
