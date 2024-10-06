package com.example.intentpractice.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "ingredientes_table")
data class IngredientesBaseModel (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome_ingredienteJson: String, // Campo para armazenar a lista convertida em JSON
    val receitaId: String?,
    val created_at: String?
) {
    fun getNomesIngrediente(): List<NomesIngredienteModel> {
        val gson = Gson()
        val listType = object : TypeToken<List<NomesIngredienteModel>>() {}.type
        return gson.fromJson(nome_ingredienteJson, listType)
    }
}
