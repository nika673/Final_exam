package com.example.Final_Project.domain.repository

import com.example.Final_Project.domain.model.Result
import com.example.Final_Project.data.model.CategoriesModel


interface CategoriesRepository {
    fun getCategoryList(
        category: String,
        callback: (categoryList: Result<List<CategoriesModel>>) -> Unit
    )
}