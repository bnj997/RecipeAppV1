package com.example.recipeapp.recipeDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.R
import com.example.recipeapp.database.Recipe
import com.example.recipeapp.databinding.FragmentFirstBinding
import com.example.recipeapp.databinding.FragmentSecondBinding
import com.example.recipeapp.recipeList.RecipeListAdapter
import com.example.recipeapp.recipeList.RecipeListViewModel
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RecipeDetailFragment : Fragment() {

    private lateinit var recipeName: String
    private lateinit var recipeMethod: String
    private lateinit var recipeDuration: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentSecondBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)

        val recipeDetailViewModel = ViewModelProvider(this).get(RecipeDetailViewModel::class.java)

        binding.recipeDetailViewModel = recipeDetailViewModel

        binding.saveButton.setOnClickListener {
            recipeName = recipe_name_editText.text.toString()
            recipeMethod = recipe_method_editText.text.toString()
            recipeDuration = recipe_duration_editText.text.toString()

            val newRecipe = Recipe(recipeName ,recipeMethod, recipeDuration)
            recipeDetailViewModel.insert(newRecipe)

            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        return binding.root
    }
}
