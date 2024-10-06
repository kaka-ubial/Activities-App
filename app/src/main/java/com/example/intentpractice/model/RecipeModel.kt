package com.example.intentpractice.model

data class Recipe (
    val id: Int,
    val receita: String,
    val ingredientes: String,
    val modo_de_preparo: String,
    val link_imagem: String,
    val tipo: String,
    val created_at: String,
    val ingredientesBase: List<BaseIngredients>
)

data class BaseIngredients (
    val id: Int,
    val nome_ingrediente: List<IngredientName>,
    val receitaId: String,
    val created_at: String
)

data class IngredientName (
    val nome: String,
    val quantidade: String
)
