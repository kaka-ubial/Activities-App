package com.example.intentpractice.recipeActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.intentpractice.databinding.ActivityRecipeListBinding
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.intentpractice.MainActivity
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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.signOutButton.apply {
            ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }


        setupRecipesModels()

    }

    private fun setupRecipesModels(){
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://api-receitas-at4n.onrender.com")
        val endpoint = retrofitClient.create(Endpoint::class.java)

        endpoint.getReceitas().enqueue(object : retrofit2.Callback<JsonArray>{

            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                if(response.isSuccessful){
                    val JsonArray = response.body()

                    JsonArray?.let {
                        val gson = Gson()
                        val receitaType = object : TypeToken<List<ReceitaModel>>() {}.type
                        val newReceitasModels: List<ReceitaModel> = gson.fromJson(it, receitaType)

                        receitasModels.clear()
                        receitasModels.addAll(newReceitasModels)

                        receitasModels.forEach { receita ->
                            println("Receita: ${receita.receita}")
                        }
                    } ?: run {
                        println("Resposta do servidor vazia")
                    }
                }
                else{
                    println("Erro na resposta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                println("Erro na requisição: ${t.message}")
            }
        })
    }
}
