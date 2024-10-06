package com.example.intentpractice.recipeActivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intentpractice.databinding.ActivityRecipeListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListActivity : AppCompatActivity() {

    private val recipeViewModel: RecipeViewModel by viewModels()
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var binding: ActivityRecipeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configuração do View Binding
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar o RecyclerView com LayoutManager e Adapter
        recipeAdapter = RecipeAdapter()
        binding.mReciclerList.apply {
            layoutManager = LinearLayoutManager(this@RecipeListActivity)
            adapter = recipeAdapter
        }

        // Observar os dados do ViewModel e atualizar o Adapter
        recipeViewModel.recipes.observe(this) { recipeList ->
            recipeList?.let {
                recipeAdapter.submitList(it)
            }
        }

        // Buscar as receitas
        recipeViewModel.fetchRecipes()

        // Listener do botão de voltar
        binding.goBackButton.setOnClickListener {
            finish()
        }
    }
}
