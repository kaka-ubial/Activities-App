package com.example.intentpractice.utils

import androidx.room.TypeConverter
import com.example.intentpractice.data.model.BaseIngredients
import com.example.intentpractice.data.model.IngredientNames
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    // Converte a lista de IngredientesBase em uma String JSON
    @TypeConverter
    fun fromIngredientesBaseList(value: List<BaseIngredients>?): String {
        return gson.toJson(value)
    }

    // Converte uma String JSON em uma lista de IngredientesBase
    @TypeConverter
    fun toIngredientesBaseList(value: String): List<BaseIngredients>? {
        val listType = object : TypeToken<List<BaseIngredients>>() {}.type
        return gson.fromJson(value, listType)
    }

    // Converte a lista de NomesIngrediente em uma String JSON
    @TypeConverter
    fun fromNomesIngredienteList(value: List<IngredientNames>?): String {
        return gson.toJson(value)
    }

    // Converte uma String JSON em uma lista de NomesIngrediente
    @TypeConverter
    fun toNomesIngredienteList(value: String): List<IngredientNames>? {
        val listType = object : TypeToken<List<IngredientNames>>() {}.type
        return gson.fromJson(value, listType)
    }
}
