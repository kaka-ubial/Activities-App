package com.example.intentpractice.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val password: String
)