package com.example.recipeapp.recipeList


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.R
import com.example.recipeapp.RecipeItem
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RecipeListFragment: Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeList = generateRecipe(25)
        recipe_list.adapter = RecipeListAdapter(recipeList)
        recipe_list.layoutManager = LinearLayoutManager(this.activity)
        recipe_list.setHasFixedSize(true)

        //Clear All Button
        view.findViewById<Button>(R.id.button_clearall).setOnClickListener {
        }

        //Add entry button
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }


    private fun generateRecipe(size: Int): List<RecipeItem> {
        val list = ArrayList<RecipeItem>()
        val drawable = R.drawable.ic_android_black_24dp
        for (i in 0 until size) {
            val recipe = RecipeItem(drawable, "item $i", "Line 1")
            list+=recipe
        }
        return list
    }


}
