package com.example.intentpractice.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.intentpractice.databinding.ActivityRecipeListBinding
import com.example.intentpractice.data.repository.RecipeRepository
import com.example.intentpractice.presentation.ui.factory.RecipeViewModelFactory
import com.example.intentpractice.data.model.Recipe
import com.example.intentpractice.data.database.AppDatabase
import com.example.intentpractice.presentation.ui.adapters.RL_RecyclerViewAdapter
import com.example.intentpractice.presentation.viewmodel.RecipeViewModel

class RecipeList : AppCompatActivity() {
    private lateinit var recipeRepository: RecipeRepository
    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var binding: ActivityRecipeListBinding
    private lateinit var adapter: RL_RecyclerViewAdapter
    private val recipeModel: ArrayList<Recipe> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receitaModelDao = AppDatabase.getDatabase(applicationContext).recipeDao()
        recipeRepository = RecipeRepository(receitaModelDao)

        recipeViewModel = ViewModelProvider(this, RecipeViewModelFactory(recipeRepository)).get(
            RecipeViewModel::class.java
        )

        binding.signOutButton.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }

        binding.goBackButton.setOnClickListener {
            startActivity(Intent(this, MainMenu::class.java))
        }

        val recyclerView: RecyclerView = binding.mReciclerList
        // Passando a lista de receitas e comportamento de clique
        adapter = RL_RecyclerViewAdapter(this, recipeModel) { recipe ->
            val intent = Intent(this, RecipeView::class.java).apply {
                putExtra("RECIPE_TITLE", recipe.receita)
                putExtra("RECIPE_TYPE", recipe.tipo)
                putExtra("RECIPE_INGREDIENTS", recipe.ingredientes)
                putExtra("RECIPE_METHOD", recipe.modo_preparo)
                putExtra("RECIPE_IMAGE", recipe.link_imagem)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe o receitasModels LiveData
        recipeViewModel.receitasModels.observe(this) { newReceitas ->
            recipeModel.clear()
            recipeModel.addAll(newReceitas)
            adapter.notifyDataSetChanged()
        }

        // Chama fetchReceitas para iniciar o fetch de dados
        recipeViewModel.fetchReceitas { /* Callback se necessário */ }
    }
}
