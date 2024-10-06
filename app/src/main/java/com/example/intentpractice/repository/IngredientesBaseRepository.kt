package com.example.intentpractice.repository

import com.example.intentpractice.dao.IngredientesBaseDao
import com.example.intentpractice.model.IngredientesBaseModel

class IngredientesBaseRepository(private val ingredientesBaseDao: IngredientesBaseDao) {
    suspend fun insert(ingredientesBase: IngredientesBaseModel) {
        ingredientesBaseDao.insert(ingredientesBase)
    }

    suspend fun getAllIngredientesBase(): List<IngredientesBaseModel> {
        return ingredientesBaseDao.getAllIngredientesBase()
    }

    suspend fun deleteAll() {
        ingredientesBaseDao.deleteAll()
    }
}
