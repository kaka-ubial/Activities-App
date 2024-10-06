package com.example.intentpractice.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.intentpractice.data.model.Recipe

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipe)

    @Query("SELECT MAX(id) FROM receita_table")
    suspend fun getMaxId(): Int?

    @Query("SELECT * FROM receita_table")
    suspend fun getAllReceitas(): List<Recipe>

    @Query("SELECT * FROM receita_table WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Recipe?

    @Query("DELETE FROM receita_table")
    suspend fun deleteAll()
}