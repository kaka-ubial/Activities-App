package com.example.intentpractice.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.intentpractice.model.ReceitaModel

@Dao
interface ReceitaModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(receitaModel: ReceitaModel)

    @Query("SELECT MAX(id) FROM receita_table")
    suspend fun getMaxId(): Int?

    @Query("SELECT * FROM receita_table")
    suspend fun getAllReceitas(): List<ReceitaModel>

    @Query("SELECT * FROM receita_table WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): ReceitaModel?

    @Query("DELETE FROM receita_table")
    suspend fun deleteAll()
}
