package com.example.intentpractice.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.intentpractice.databinding.ActivityRecipeListBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
            RecipeViewModel::class.java)

        binding.signOutButton.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }

        binding.goBackButton.setOnClickListener {
            startActivity(Intent(this, MainMenu::class.java))
        }

        val recyclerView: RecyclerView = binding.mReciclerList
        adapter = RL_RecyclerViewAdapter(this, recipeModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        recipeViewModel.fetchReceitas {
            recipeModel.clear()  // Limpa a lista antiga
            recipeModel.addAll(recipeViewModel.receitasModels)  // Adiciona as novas receitas
            adapter.notifyDataSetChanged()  // Notifica o adapter sobre as mudanças
        }
    }


}
