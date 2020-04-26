package com.example.recipeapp.recipeDetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.database.Recipe
import com.example.recipeapp.database.RecipeDatabase
import com.example.recipeapp.database.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RecipeDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecipeRepository

    init {
        val recipeDao = RecipeDatabase.getInstance(application, viewModelScope).recipeDatabaseDao()
        repository = RecipeRepository(recipeDao)
    }


    fun insert(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(recipe)
        }
    }


}