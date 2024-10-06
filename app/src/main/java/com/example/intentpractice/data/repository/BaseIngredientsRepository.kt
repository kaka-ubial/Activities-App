package com.example.intentpractice.data.repository

import com.example.intentpractice.data.dao.BaseIngredientsDao
import com.example.intentpractice.data.model.BaseIngredients

class BaseIngredientsRepository(private val ingredientesBaseDao: BaseIngredientsDao) {
    suspend fun insert(ingredientesBase: BaseIngredients) {
        ingredientesBaseDao.insert(ingredientesBase)
    }

    suspend fun getAllIngredientesBase(): List<BaseIngredients> {
        return ingredientesBaseDao.getAllIngredientesBase()
    }

    suspend fun deleteAll() {
        ingredientesBaseDao.deleteAll()
    }
}
