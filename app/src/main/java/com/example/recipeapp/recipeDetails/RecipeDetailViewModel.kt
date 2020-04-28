package com.example.recipeapp.recipeDetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.database.Recipe
import com.example.recipeapp.database.RecipeDatabase
import com.example.recipeapp.database.RecipeRepository
import kotlinx.coroutines.*


class RecipeDetailViewModel(application: Application) : AndroidViewModel(application) {

    /** CONSTRUCTOR STUFF **/
    private val repository: RecipeRepository
    init {
        val recipeDao = RecipeDatabase.getInstance(application, viewModelScope).recipeDatabaseDao()
        repository = RecipeRepository(recipeDao)
    }
    /** HANDLES THE COROUTINE LOGIC **/
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    /** THE EDITTEXT BOXES **/
    val recipeName = MutableLiveData<String>()
    val recipeMethod = MutableLiveData<String>()
    val recipeDuration = MutableLiveData<String>()


    /** HANDLES SNACKBAR ERROR IF NOT ENTRIES FILLED OUT **/
    private var _snackBarMessage = MutableLiveData<Boolean>()
    val snackBarMessage: LiveData<Boolean>
        get() = _snackBarMessage
    fun doneShowingError() {
        _snackBarMessage.value = false
    }

    /** HANDLES SAVE RECIPE STATUS IF ENTRIES FILLED OUT PROPERLY **/
    private var _savedRecipe = MutableLiveData<Boolean>()
    val savedRecipe: LiveData<Boolean>
        get() = _savedRecipe
    fun doneSavingRecipe() {
        _savedRecipe.value = false
    }


    /** HANDLES INSERT FUNCTIONALITY **/
    fun onAddNewRecipe() {
        uiScope.launch {
            val name = recipeName.value
            val method = recipeMethod.value
            val duration = recipeDuration.value
            if (name == null || method == null || duration == null) {
                _snackBarMessage.value = true
            } else {
                val newRecipe = Recipe(name, method, duration)
                insert(newRecipe)
                _savedRecipe.value = true
            }
        }
    }
    private suspend fun insert(recipe: Recipe) {
        withContext(Dispatchers.IO) {
            repository.insert(recipe)
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