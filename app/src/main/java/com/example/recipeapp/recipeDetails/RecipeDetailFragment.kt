package com.example.recipeapp.recipeDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentSecondBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RecipeDetailFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentSecondBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)
        val recipeDetailViewModel = ViewModelProvider(this).get(RecipeDetailViewModel::class.java)
        binding.recipeDetailViewModel = recipeDetailViewModel
        binding.lifecycleOwner = this


        /** OBSERVES SAVEDRECIPE VARIABLE SO CAN MOVE TO LIST FRAGMENT ONCE DONE **/
        recipeDetailViewModel.savedRecipe.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                recipeDetailViewModel.doneSavingRecipe()
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        })


        /** OBSERVES ERROR STATUS AND SHOWS ERROR IF ENTRY NOT ADDED **/
        recipeDetailViewModel.snackBarMessageError.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    "Please complete all boxes",
                    Snackbar.LENGTH_SHORT
                ).show()
                recipeDetailViewModel.doneShowingError()
            }
        })

        return binding.root
    }
}
