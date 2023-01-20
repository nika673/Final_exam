package com.example.Final_Project.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.Final_Project.R
import com.example.Final_Project.adapter.CategoriesAdapter
import com.example.Final_Project.data.Api.retrofit.categories.CategoriesApiImpl
import com.example.Final_Project.data.Api.retrofit.categories.CategoriesRetrofitApi
import com.example.Final_Project.data.CategoriesRepositoryImpl
import com.example.Final_Project.databinding.ActivityMainBinding
import com.example.Final_Project.domain.model.Result
import com.example.Final_Project.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var logout: Button
    private lateinit var recyclerView: RecyclerView


    private val adapter = CategoriesAdapter()
    val bundle = Bundle()

    private val categoriesRepository = CategoriesRepositoryImpl(
        Executors.newCachedThreadPool(), Handler(
            Looper.getMainLooper()
        ),
        CategoriesApiImpl(
            Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(CategoriesRetrofitApi::class.java)
        )
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = Navigation.findNavController(view)
        recyclerView = view.findViewById(R.id.recycler)
        val fragment = HomeFragment()


        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        recyclerView.adapter = adapter

        loadInfo()

        logout = view.findViewById(R.id.out)
        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@HomeFragment.requireContext(), MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }


        adapter.onClicked = {

            bundle.putString("id", it.idCategory)
            bundle.putString("text", it.strCategory)
            bundle.putString("imageUrl", it.strCategoryThumb)
            fragment.arguments = bundle
            val categories = HomeFragmentDirections.actionHomeFragmentToBrowseByCategoryFragment()
            navController.navigate(categories)
        }
    }

    private fun loadInfo() {
        categoriesRepository.getCategoryList("categories") { result ->
            when (result) {
                is Result.Success -> {
                    adapter.submitList(result.data)
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