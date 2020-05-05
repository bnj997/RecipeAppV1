package com.example.recipeapp.recipeView

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentThirdBinding


class RecipeViewFragment: Fragment() {

    private val args: RecipeViewFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)

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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.view_fragment_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                findNavController().navigate(RecipeViewFragmentDirections.actionThirdFragmentToSecondFragment(args.recipeId))
                true
            }
            else -> false
        }
    }


}