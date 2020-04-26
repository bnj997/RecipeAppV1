package com.example.recipeapp.recipeList


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
import com.example.recipeapp.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar



/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RecipeListFragment: Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentFirstBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)

        val recipeListViewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)

        binding.recipeListViewModel = recipeListViewModel

        val adapter = RecipeListAdapter()
        binding.recyclerViewList.adapter = adapter

        binding.lifecycleOwner = this


        /** DISPLAYS LIST CURRENTLY IN DATABASE
         * Also updates the list once stuff is added to the list
         */
        recipeListViewModel.allRecipes.observe(viewLifecycleOwner, Observer { recipes ->
            recipes?.let { adapter.submitList(it) }
        })


        /** OBSERVES SNACKBAR VARIABLE
         * Handles the snackbar that displays to user list has been cleared
         * */
        recipeListViewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.snackbar_clearall),
                    Snackbar.LENGTH_SHORT
                ).show()
                recipeListViewModel.doneShowingSnackbar()
            }
        })


        /** GOES TO RECIPEDETAIL SCREEN ONCE CLICKED**/
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        return binding.root
    }

}
