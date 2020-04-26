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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentFirstBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RecipeListFragment: Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentFirstBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)

        val recipeListViewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)

        binding.recyclerViewList.layoutManager = LinearLayoutManager(this.activity)

        binding.recipeListViewModel = recipeListViewModel

        val adapter = RecipeListAdapter()
        binding.recyclerViewList.adapter = adapter

        /** Displays the list currently in database
         * Also updates the list once stuff is added to the list
         */
        recipeListViewModel.allRecipes.observe(viewLifecycleOwner, Observer { recipes ->
            recipes?.let { adapter.submitList(it) }
        })

        binding.buttonClearall.setOnClickListener {
            recipeListViewModel.deleteAllRecipes()
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        return binding.root
    }

}
