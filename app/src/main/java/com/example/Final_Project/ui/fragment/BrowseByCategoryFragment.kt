package com.example.Final_Project.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.Final_Project.R
import com.example.Final_Project.adapter.BrowseByCategoryAdapter
import com.example.Final_Project.databinding.ActivityMainBinding
import com.example.Final_Project.domain.model.BrowseByCategory
import com.example.Final_Project.domain.model.Result
import com.example.Final_Project.ui.MainActivity
import com.example.Final_Project.ui.viewmodel.BrowseByCategoryViewModel
import com.google.android.material.snackbar.Snackbar


class BrowseByCategoryFragment : Fragment(R.layout.fragment_browse_by_category) {

    private val adapter = BrowseByCategoryAdapter()
    private lateinit var logout: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var textView2: TextView
    private lateinit var image: ImageView


    val viewModel: BrowseByCategoryViewModel by lazy {
        ViewModelProvider(this)[BrowseByCategoryViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView = view.findViewById(R.id.secondRecycler)
        textView2 = view.findViewById(R.id.textView2)
        image = view.findViewById(R.id.image2)


        val bundle = Bundle()
        val fragment = BrowseByCategoryFragment()
        val navController = Navigation.findNavController(view)

        val linearLayoutManager =
            LinearLayoutManager(this@BrowseByCategoryFragment.context, RecyclerView.VERTICAL, true)

        val text = arguments?.getString("text")
        val imageUrl = arguments?.getString("imageUrl")



        recyclerView.layoutManager = linearLayoutManager
        textView2.text = text

        adapter.categortText = text.toString()

        Glide.with(this)
            .load(imageUrl)
            .into(image)

        recyclerView.adapter = adapter

        adapter.onClicked2 = {

            bundle.putString("mealId", it.idMeal)
            bundle.putString("mealName", it.strMeal)
            bundle.putString("mealImage", it.strMealThumb)
            fragment.arguments = bundle
            val categories = BrowseByCategoryFragmentDirections.actionBrowseByCategoryFragmentToReceiptFragment()
            navController.navigate(categories)


        }
        viewModel.categoryNameLiveData.observe(this@BrowseByCategoryFragment.requireActivity()) {
            text.toString()
        }
        viewModel.loadMeals(text.toString())

        viewModel.browseByCategoryResultLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success<*> -> {
                    adapter.submitList(result.data as MutableList<BrowseByCategory>?)
                }


                is Result.Error -> {
                    Snackbar.make(
                        ActivityMainBinding.inflate(layoutInflater).root,
                        "ar chaitvirta",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


}