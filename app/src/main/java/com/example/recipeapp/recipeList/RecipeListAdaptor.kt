package com.example.recipeapp.recipeList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.database.Recipe
import com.example.recipeapp.databinding.ListItemRecipeBinding




class RecipeListAdapter(private val viewModel: RecipeListViewModel) : ListAdapter<Recipe, RecipeListAdapter.RecipeViewHolder>(RecipeDiffCallback()) {

    /** CALLED BY RECYCLERVIEW TO DISPLAY DATA AT SPECIFIED POSITION
     *  Do not inflate the list_item_recipe xml here since otherwise, this method will know too much info about the view holder
     *  The viewholder should know how to create itself, and so the logic should be within the viewholder class itself
     * **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        /** Instantiates a new view holder from view holder class since view holder should know what value to inflate**/
        return RecipeViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item)
    }


    /** PRIVATE CONSTRUCTOR SO CAN ONLY BE CALLED FROM INSIDE THE CLASS
     * "from" IS ON COMPANION OBJECT, IT CAN STILL CALL CONSTRUCTOR BUT ANOTHER CLASS COULD NOT CALL CONSTRUCTOR DIRECTLY
     * **/
    class RecipeViewHolder private constructor (private val binding: ListItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {

        /** THE VIEWHOLDER KNOWS BEST HOW TO BIND DATA AND UPDATE ITSELF
         *  THIS FUNCTION IS THEREFORE IN THE RECIPEVIEWHOLDER CLASS NOT THE onBindViewHolderMethod WHICH IS DONE USUALLY
         *  Helps when you want to include more view holders in this adapter
         *  Aims to get data from the recipe and put in the right places
         *  **/
        fun bind(viewModel: RecipeListViewModel, item: Recipe) {
            binding.viewModel = viewModel
            binding.recipe = item
            binding.executePendingBindings()
        }

        /** WANT TO CALL FROM VIEWHOLDER CLASS itself AND NOT FROM INSTANCE OF A VIEW HOLDER
         *
         * **/
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