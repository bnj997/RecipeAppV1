package com.example.recipeapp.recipedetail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.R
import com.example.recipeapp.database.Recipe
import com.example.recipeapp.recipeList.RecipeListViewModel
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RecipeDetailFragment : Fragment() {

    private lateinit var recipeDetailViewModel: RecipeDetailViewModel

    private lateinit var recipeName: String
    private lateinit var recipeMethod: String
    private lateinit var recipeDuration: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipeDetailViewModel = ViewModelProvider(this).get(RecipeDetailViewModel::class.java)

        view.findViewById<Button>(R.id.save_button).setOnClickListener {
            recipeName = recipe_name_editText.text.toString()
            recipeMethod = recipe_method_editText.text.toString()
            recipeDuration = recipe_duration_editText.text.toString()

            val newRecipe = Recipe(recipeName ,recipeMethod, recipeDuration)
            recipeDetailViewModel.insert(newRecipe)

            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

    }
}
