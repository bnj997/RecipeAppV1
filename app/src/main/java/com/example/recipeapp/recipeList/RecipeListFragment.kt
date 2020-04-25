package com.example.recipeapp.recipeList


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.R
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RecipeListFragment: Fragment() {

    private lateinit var recipeListViewModel: RecipeListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RecipeListAdapter(requireActivity())

        recipe_list.adapter = adapter
        recipe_list.layoutManager = LinearLayoutManager(this.activity)

        recipeListViewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)

        recipeListViewModel.allRecipes.observe(viewLifecycleOwner, Observer { recipes ->
            recipes?.let { adapter.setRecipes(it) }
        })


        //Clear All Button
        view.findViewById<Button>(R.id.button_clearall).setOnClickListener {
        }

        //Add entry button
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }


}
