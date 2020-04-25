package com.example.recipeapp.recipeList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.database.Recipe

class RecipeListAdapter internal constructor(context: Context) : RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var recipeList = emptyList<Recipe>() // Cached copy of words

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val recipeItemView = inflater.inflate(R.layout.list_item_recipe, parent, false)
        return RecipeViewHolder(recipeItemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipeItem = recipeList[position]
        holder.imageView.setImageResource(R.drawable.ic_android_black_24dp)
        holder.recipeNameTextView.text = currentRecipeItem.recipeName
        holder.recipeDurationTextView.text = currentRecipeItem.recipeDuration
    }

    internal fun setRecipes(recipes: List<Recipe>) {
        this.recipeList = recipes
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }




    class RecipeViewHolder(recipeItemView: View) : RecyclerView.ViewHolder(recipeItemView) {
        val imageView: ImageView = recipeItemView.findViewById(R.id.recipe_image)
        val recipeNameTextView: TextView = recipeItemView.findViewById(R.id.recipe_name)
        val recipeDurationTextView: TextView = recipeItemView.findViewById(R.id.recipe_duration)
    }

}