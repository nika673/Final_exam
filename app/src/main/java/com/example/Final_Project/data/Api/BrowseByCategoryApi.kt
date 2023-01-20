package com.example.Final_Project.data.Api

import com.example.Final_Project.data.model.BrowseByCategoryModel


interface BrowseByCategoryApi {
    fun fetchMeals(meals: String): List<BrowseByCategoryModel>
}