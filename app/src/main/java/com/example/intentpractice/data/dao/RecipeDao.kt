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

    @Query("SELECT MAX(id) FROM receitas")
    suspend fun getMaxId(): Int?

    @Query("SELECT * FROM receitas")
    suspend fun getAllReceitas(): List<Recipe>

    @Query("SELECT * FROM receitas WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Recipe?

    @Query("DELETE FROM receitas")
    suspend fun deleteAll()
}
