package com.example.recipeapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_table")
data class Recipe(
    @ColumnInfo(name = "recipe_name")
    var recipeName: String,

    @ColumnInfo(name = "recipe_method")
    var recipeMethod: String,

    @ColumnInfo(name = "recipe_duration ")
    var recipeDuration: String
) {
    @PrimaryKey(autoGenerate = true)
    var recipeId: Long = 0L
}