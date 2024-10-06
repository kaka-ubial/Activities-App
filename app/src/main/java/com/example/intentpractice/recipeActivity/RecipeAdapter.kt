package com.example.intentpractice.recipeActivity

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.intentpractice.R
import com.example.intentpractice.model.Recipe

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.MyViewHolder>() {

    private var recipeModels: List<Recipe> = listOf()

    fun submitList(recipes: List<Recipe>) {
        Log.d("RecipeAdapter", "Submitting list of recipes: $recipes")
        this.recipeModels = recipes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeAdapter.MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.recipe_row, parent, false)
        return RecipeAdapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeAdapter.MyViewHolder, position: Int) {
        val recipe = recipeModels[position]
        Log.d("RecipeAdapter", "Binding recipe: $recipe")
        holder.nameTextView.text = recipe.receita
        holder.timeTextView.text = recipe.tipo
        holder.button.setOnClickListener {
            Log.d("RecipeAdapter", "Button clicked for recipe: ${recipe.receita}")
        }
    }

    override fun getItemCount(): Int {
        return this.recipeModels.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView2)
        val nameTextView: TextView = itemView.findViewById(R.id.textView4)
        val timeTextView: TextView = itemView.findViewById(R.id.textView5)
        val button: Button = itemView.findViewById(R.id.button4)
    }
}
