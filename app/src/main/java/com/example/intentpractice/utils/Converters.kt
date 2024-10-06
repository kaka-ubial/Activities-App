package com.example.intentpractice.utils

import androidx.room.TypeConverter
import com.example.intentpractice.model.IngredientesBaseModel
import com.example.intentpractice.model.NomesIngredienteModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    // Converte a lista de IngredientesBase em uma String JSON
    @TypeConverter
    fun fromIngredientesBaseList(value: List<IngredientesBaseModel>?): String {
        return gson.toJson(value)
    }

    // Converte uma String JSON em uma lista de IngredientesBase
    @TypeConverter
    fun toIngredientesBaseList(value: String): List<IngredientesBaseModel>? {
        val listType = object : TypeToken<List<IngredientesBaseModel>>() {}.type
        return gson.fromJson(value, listType)
    }

    // Converte a lista de NomesIngrediente em uma String JSON
    @TypeConverter
    fun fromNomesIngredienteList(value: List<NomesIngredienteModel>?): String {
        return gson.toJson(value)
    }

    // Converte uma String JSON em uma lista de NomesIngrediente
    @TypeConverter
    fun toNomesIngredienteList(value: String): List<NomesIngredienteModel>? {
        val listType = object : TypeToken<List<NomesIngredienteModel>>() {}.type
        return gson.fromJson(value, listType)
    }
}
