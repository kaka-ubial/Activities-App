package com.example.intentpractice.recipeActivity

data class ReceitaModel (
    val id: Int,
    val receita: String,
    val ingredientes: String,
    val modo_de_preparo: String,
    val link_imagem: String,
    val tipo: String,
    val created_at: String,
    val ingredientesBase: List<IngredientesBase>
)

data class IngredientesBase (
    val id: Int,
    val nome_ingrediente: List<NomesIngrediente>,
    val receitaId: String,
    val created_at: String
)

data class NomesIngrediente (
    val nome: String,
    val quantidade: String
)
