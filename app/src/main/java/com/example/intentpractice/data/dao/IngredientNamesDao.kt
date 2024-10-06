package com.example.intentpractice.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.intentpractice.data.model.IngredientNames

@Dao
interface IngredientNamesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(nomesIngrediente: IngredientNames)

    @Query("SELECT * FROM nomes_ingrediente_table")
    suspend fun getAllNomesIngrediente(): List<IngredientNames>

    @Query("DELETE FROM nomes_ingrediente_table")
    suspend fun deleteAll()
}
