package com.example.Final_Project.data.Api

import com.example.Final_Project.data.model.CategoriesModel


interface CategoriesApi {
    fun fetchCategory(categories: String): List<CategoriesModel>
}