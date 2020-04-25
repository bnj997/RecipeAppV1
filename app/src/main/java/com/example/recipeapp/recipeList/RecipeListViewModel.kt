package com.example.recipeapp.recipeList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.database.Recipe
import com.example.recipeapp.database.RecipeDatabase
import com.example.recipeapp.database.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RecipeListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecipeRepository

    val allRecipes: LiveData<List<Recipe>>

    init {
        val recipeDao = RecipeDatabase.getInstance(application, viewModelScope).recipeDatabaseDao()
        repository = RecipeRepository(recipeDao)
        allRecipes = repository.allRecipes
    }

    fun insert(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(recipe)
        }
    }

}