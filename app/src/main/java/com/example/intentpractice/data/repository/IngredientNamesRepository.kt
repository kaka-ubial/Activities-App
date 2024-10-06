package com.example.intentpractice.data.repository

import com.example.intentpractice.data.dao.IngredientNamesDao
import com.example.intentpractice.data.model.IngredientNames

class IngredientNamesRepository(private val ingredientNamesDao: IngredientNamesDao) {
    suspend fun insert(nomesIngrediente: IngredientNames) {
        ingredientNamesDao.insert(nomesIngrediente)
    }

    suspend fun getAllNomesIngrediente(): List<IngredientNames> {
        return ingredientNamesDao.getAllNomesIngrediente()
    }

    suspend fun deleteAll() {
        ingredientNamesDao.deleteAll()
    }
}
