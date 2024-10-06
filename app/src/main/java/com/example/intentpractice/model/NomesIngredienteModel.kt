package com.example.intentpractice.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nomes_ingrediente_table")
data class NomesIngredienteModel (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String?,
    val quantidade: String?
    )