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

class RL_RecyclerViewAdapter(private val context: Context, private val receitasModels: ArrayList<Recipe>) : RecyclerView.Adapter<RL_RecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.recipe_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameTextView.text = receitasModels[position].receita
        holder.timeTextView.text = receitasModels[position].tipo
        holder.button.setOnClickListener {
            println("Botão clicado")
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
}