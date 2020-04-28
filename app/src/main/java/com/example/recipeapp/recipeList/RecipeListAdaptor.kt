package com.example.recipeapp.recipeList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.database.Recipe
import com.example.recipeapp.databinding.ListItemRecipeBinding




class RecipeListAdapter(private val viewModel: RecipeListViewModel) : ListAdapter<Recipe, RecipeListAdapter.RecipeViewHolder>(RecipeDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item)
    }


    class RecipeViewHolder private constructor (val binding: ListItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: RecipeListViewModel, item: Recipe) {
            binding.viewModel = viewModel
            binding.recipe = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): RecipeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemRecipeBinding.inflate(layoutInflater, parent, false)
                return RecipeViewHolder(binding)
            }
        }
    }
}


class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {

    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.recipeId == newItem.recipeId
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.recipeName == newItem.recipeName &&
                oldItem.recipeDuration == newItem.recipeDuration &&
                oldItem.recipeMethod == newItem.recipeMethod
    }

}