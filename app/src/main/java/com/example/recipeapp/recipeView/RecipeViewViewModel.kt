package com.example.recipeapp.recipeView

import android.app.Application
import androidx.lifecycle.*
import com.example.recipeapp.database.Recipe
import com.example.recipeapp.database.RecipeDatabase
import com.example.recipeapp.database.RecipeRepository
import kotlinx.coroutines.*

class RecipeViewViewModel(application: Application) : AndroidViewModel(application) {

    /** CONSTRUCTOR STUFF **/
    private val repository: RecipeRepository
    init {
        val recipeDao = RecipeDatabase.getInstance(application, viewModelScope).recipeDatabaseDao()
        repository = RecipeRepository(recipeDao)
    }
    /** HANDLES THE COROUTINE LOGIC **/
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val recipeName = MutableLiveData<String>()
    val recipeMethod = MutableLiveData<String>()
    val recipeDuration = MutableLiveData<String>()


    fun initialise(recipeId: String) {
        uiScope.launch {
            val thisRecipe = getThisRecipe(recipeId)
            recipeName.value = thisRecipe.recipeName
            recipeMethod.value = thisRecipe.recipeMethod
            recipeDuration.value = thisRecipe.recipeDuration
        }
    }
    private suspend fun getThisRecipe(recipeId: String): Recipe {
        return withContext(Dispatchers.IO) {
            repository.getThisRecipe(recipeId)
        }
    }


    /** CANCEL ALL COROUTINES
     * Called when the ViewModel is dismantled.
     *
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}