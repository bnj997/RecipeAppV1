package com.example.recipeapp.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface RecipeDatabaseDao {

    @Insert
    fun insert(recipe: Recipe)

    @Update
    fun update(recipe: Recipe)

    @Delete
    fun deleteThisRecipe(recipe: Recipe)

    @Query("DELETE FROM recipe_table")
    fun deleteAllRecipes()

    @Query("SELECT * FROM recipe_table ORDER BY recipeId DESC")
    fun getAllRecipes(): LiveData<List<Recipe>>

}