package com.example.recipeapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_table")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    var recipeId: Int,

    @ColumnInfo(name = "recipe_name")
    val recipeName: String,

    @ColumnInfo(name = "recipe_method")
    var recipeMethod: String,

    @ColumnInfo(name = "recipe_duration ")
    var recipeDuration: Int
)