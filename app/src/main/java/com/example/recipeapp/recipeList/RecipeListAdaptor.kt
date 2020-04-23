package com.example.recipeapp.recipeList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.RecipeItem

class RecipeListAdapter(private val recipeList: List<RecipeItem>) : RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(recipeItemView: View) : RecyclerView.ViewHolder(recipeItemView) {
        val imageView: ImageView = recipeItemView.findViewById(R.id.recipe_image)
        val textView1: TextView = recipeItemView.findViewById(R.id.recipe_name)
        val textView2: TextView = recipeItemView.findViewById(R.id.recipe_duration)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val recipeItemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_recipe, parent, false)
        return RecipeViewHolder(recipeItemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipeItem = recipeList[position]
        holder.imageView.setImageResource(currentRecipeItem.ImageResource)
        holder.textView1.text = currentRecipeItem.text1
        holder.textView2.text = currentRecipeItem.text2
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}