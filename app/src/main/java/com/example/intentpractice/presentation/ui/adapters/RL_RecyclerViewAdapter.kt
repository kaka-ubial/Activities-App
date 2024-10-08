package com.example.intentpractice.presentation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.intentpractice.R
import com.example.intentpractice.data.model.Recipe
import com.squareup.picasso.Picasso

class RL_RecyclerViewAdapter(
    private val context: Context,
    private val receitasModels: ArrayList<Recipe>,
    private val onRecipeClick: (Recipe) -> Unit  // Função callback para o clique
) : RecyclerView.Adapter<RL_RecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.recipe_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val recipe = receitasModels[position]
        holder.nameTextView.text = recipe.receita
        holder.timeTextView.text = recipe.tipo
        holder.button.setOnClickListener {
            println("Botão clicado")
        }
        println(recipe.link_imagem)
        Picasso.get().load(recipe.link_imagem).into(holder.imageView)


        // Clique no item da receita
        holder.itemView.setOnClickListener {
            onRecipeClick(recipe)  // Chama a função passada como argumento
        }
    }

    override fun getItemCount(): Int {
        return this.receitasModels.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView2)
        val nameTextView: TextView = itemView.findViewById(R.id.textView4)
        val timeTextView: TextView = itemView.findViewById(R.id.textView5)
        val button: Button = itemView.findViewById(R.id.button4)
    }

    fun updateReceitas(novasReceitas: List<Recipe>) {
        receitasModels.clear()
        receitasModels.addAll(novasReceitas)
        notifyDataSetChanged()  // Notifica o RecyclerView que os dados mudaram
    }
}
