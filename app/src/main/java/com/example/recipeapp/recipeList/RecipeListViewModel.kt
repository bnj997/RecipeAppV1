package com.example.recipeapp.recipeList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.database.Recipe
import com.example.recipeapp.database.RecipeDatabase
import com.example.recipeapp.database.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.*


class RecipeListViewModel(application: Application) : AndroidViewModel(application) {

    /** CONSTRUCTOR STUFF **/
    private val repository: RecipeRepository
    val allRecipes: LiveData<List<Recipe>>
    init {
        val recipeDao = RecipeDatabase.getInstance(application, viewModelScope).recipeDatabaseDao()
        repository = RecipeRepository(recipeDao)
        allRecipes = repository.allRecipes
    }
    /** HANDLES THE COROUTINE LOGIC **/
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)




    /** HANDLES SNACKBAR LOGIC
     * Boolean that is observed by fragment and changes value once you click on clear button
     **/
    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent
    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

    /** HANDLES LOGIC WHEN PRESSING ON LIST ITEM
     **/
    private var _openTaskEvent = MutableLiveData<Long>()
    val openTaskEvent: LiveData<Long>
        get() = _openTaskEvent
    fun openRecipe(recipeId: Long) {
        _openTaskEvent.value = recipeId
    }


    /** HANDLES LOGIC WHEN DELETING RECIPE
     * Deletes recipes from database and also alters snackbar value to true
     * **/
    fun onDeleteAllRecipes() {
        uiScope.launch {
            deleteAllRecipes()
        }
        _showSnackbarEvent.value = true
    }
    private suspend fun deleteAllRecipes() {
        withContext(Dispatchers.IO) {
            repository.deleteAllRecipes()
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