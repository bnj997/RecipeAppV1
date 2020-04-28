package com.example.recipeapp.recipeView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentThirdBinding
import com.example.recipeapp.recipeList.RecipeListFragmentDirections


class RecipeViewFragment: Fragment() {

    private val args: RecipeViewFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentThirdBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_third, container, false)
        val recipeViewViewModel = ViewModelProvider(this).get(RecipeViewViewModel::class.java)
        binding.recipeViewViewModel = recipeViewViewModel
        binding.lifecycleOwner = this

        recipeViewViewModel.initialise(args.recipeId)


        binding.submitButton.setOnClickListener {
            findNavController().navigate(RecipeViewFragmentDirections.actionThirdFragmentToFirstFragment())
        }


        return binding.root

    }

}