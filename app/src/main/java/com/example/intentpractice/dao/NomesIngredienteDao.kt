package com.example.intentpractice.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.intentpractice.model.NomesIngredienteModel

@Dao
interface NomesIngredienteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(nomesIngrediente: NomesIngredienteModel)

    @Query("SELECT * FROM nomes_ingrediente_table")
    suspend fun getAllNomesIngrediente(): List<NomesIngredienteModel>

    @Query("DELETE FROM nomes_ingrediente_table")
    suspend fun deleteAll()
}
