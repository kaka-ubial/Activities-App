package com.example.intentpractice.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.intentpractice.data.model.BaseIngredients

@Dao
interface BaseIngredientsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ingredientesBase: BaseIngredients)

    @Query("SELECT * FROM ingredientes_table")
    suspend fun getAllIngredientesBase(): List<BaseIngredients>

    @Query("DELETE FROM ingredientes_table")
    suspend fun deleteAll()
}
