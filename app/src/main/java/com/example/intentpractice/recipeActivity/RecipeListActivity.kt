package com.example.intentpractice.recipeActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.intentpractice.databinding.ActivityRecipeListBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.intentpractice.Login
import com.example.intentpractice.MainMenu
import com.example.intentpractice.repository.ReceitaRepository
import com.example.intentpractice.ViewModel.RecipeViewModelFactory
import com.example.intentpractice.model.ReceitaModel
import com.example.intentpractice.database.AppDatabase
import com.example.intentpractice.R


class RecipeListActivity : AppCompatActivity() {
    private lateinit var recipeRepository: ReceitaRepository
    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var binding: ActivityRecipeListBinding
    private lateinit var adapter: RL_RecyclerViewAdapter
    private val receitasModels: ArrayList<ReceitaModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receitaModelDao = AppDatabase.getDatabase(applicationContext).receitaModelDao()
        recipeRepository = ReceitaRepository(receitaModelDao)

        recipeViewModel = ViewModelProvider(this, RecipeViewModelFactory(recipeRepository)).get(RecipeViewModel::class.java)

        binding.signOutButton.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }

        binding.goBackButton.setOnClickListener {
            startActivity(Intent(this, MainMenu::class.java))
        }

        val recyclerView: RecyclerView = binding.mReciclerList
        adapter = RL_RecyclerViewAdapter(this, receitasModels)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        recipeViewModel.fetchReceitas {
            receitasModels.clear()  // Limpa a lista antiga
            receitasModels.addAll(recipeViewModel.receitasModels)  // Adiciona as novas receitas
            adapter.notifyDataSetChanged()  // Notifica o adapter sobre as mudanças
        }
    }


}
