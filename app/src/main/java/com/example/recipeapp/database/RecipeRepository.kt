package com.example.recipeapp.database

import android.app.Application
import android.os.AsyncTask
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class RecipeRepository(private val recipeDao: RecipeDatabaseDao) {

    val allRecipes: LiveData<List<Recipe>> = recipeDao.getAllRecipes()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(recipe: Recipe) {
        recipeDao.insert(recipe)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(recipe: Recipe) {
        recipeDao.update(recipe)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteThisRecipe(recipe: Recipe) {
        recipeDao.deleteThisRecipe(recipe)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAllRecipes() {
        recipeDao.deleteAllRecipes()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getLatestRecipe() {
        recipeDao.getLatestRecipe()
    }

}