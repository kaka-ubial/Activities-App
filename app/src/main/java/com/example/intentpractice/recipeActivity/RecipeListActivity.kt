package com.example.intentpractice.recipeActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.intentpractice.databinding.ActivityRecipeListBinding
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.intentpractice.Login
import com.example.intentpractice.MainMenu
import com.example.intentpractice.R
import com.example.intentpractice.api.Endpoint
import com.example.intentpractice.utils.NetworkUtils
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Response

class RecipeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeListBinding
    val receitasModels: ArrayList<ReceitaModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signOutButton.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        binding.goBackButton.setOnClickListener {
            val goBack = Intent(this, MainMenu::class.java)
            startActivity(goBack)
        }

        binding.signOutButton.apply {
            ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        val reciclerView: RecyclerView = findViewById(R.id.mReciclerList)

        val adapter: RL_RecyclerViewAdapter = RL_RecyclerViewAdapter(this, receitasModels)

        reciclerView.adapter = adapter
        reciclerView.layoutManager = LinearLayoutManager(this)


        setupRecipesModels{
            adapter.notifyDataSetChanged()
            println(receitasModels)
        }

    }

    private fun setupRecipesModels(onDataFetched: () -> Unit) {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://api-receitas-at4n.onrender.com")
        val endpoint = retrofitClient.create(Endpoint::class.java)

        endpoint.getReceitas().enqueue(object : retrofit2.Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                if (response.isSuccessful) {
                    response.body()?.let { jsonArray ->
                        val gson = Gson()
                        val receitaType = object : TypeToken<List<ReceitaModel>>() {}.type
                        val newReceitasModels: List<ReceitaModel> = gson.fromJson(jsonArray, receitaType)

                        // Clear the existing data and add new data
                        receitasModels.clear()
                        receitasModels.addAll(newReceitasModels)

                        // Log each recipe for debugging
                        receitasModels.forEach { receita ->
                            println("Receita: ${receita.receita}")
                        }

                        // Invoke the callback to update the RecyclerView
                        onDataFetched()
                    } ?: run {
                        println("Resposta do servidor vazia")
                    }
                } else {
                    println("Erro na resposta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                println("Erro na requisição: ${t.message}")
            }
        })
    }
}
