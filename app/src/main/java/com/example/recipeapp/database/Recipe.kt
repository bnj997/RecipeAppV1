package com.example.recipeapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "recipe_table")
data class Recipe constructor(
    @ColumnInfo(name = "recipe_name")
    var recipeName: String = "",

    @ColumnInfo(name = "recipe_method")
    var recipeMethod: String = "",

    @ColumnInfo(name = "recipe_duration ")
    var recipeDuration: String = "",

    @PrimaryKey @ColumnInfo(name = "recipeid")
    var recipeId: String = UUID.randomUUID().toString()
)

