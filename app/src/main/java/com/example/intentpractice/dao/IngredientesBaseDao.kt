package com.example.intentpractice.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.intentpractice.model.IngredientesBaseModel

@Dao
interface IngredientesBaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ingredientesBase: IngredientesBaseModel)

    @Query("SELECT * FROM ingredientes_table")
    suspend fun getAllIngredientesBase(): List<IngredientesBaseModel>

    @Query("DELETE FROM ingredientes_table")
    suspend fun deleteAll()
}
