package com.example.intentpractice.recipeActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intentpractice.model.Recipe
import com.example.intentpractice.repository.RecipeRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.reflect.Type
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    fun fetchRecipes() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    recipeRepository.getRecipes().execute()
                }

                if (response.isSuccessful) {
                    val jsonArray = response.body()
                    jsonArray?.let {
                        val recipeListType: Type = object : TypeToken<List<Recipe>>() {}.type

                        val recipeList: List<Recipe> = Gson().fromJson(it, recipeListType)

                        _recipes.postValue(recipeList)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
