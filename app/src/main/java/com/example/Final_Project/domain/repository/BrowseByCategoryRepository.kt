package com.example.Final_Project.domain.repository

import com.example.Final_Project.domain.model.BrowseByCategory
import com.example.Final_Project.domain.model.Result


interface BrowseByCategoryRepository {
    fun getMealsList(meals: String): Result<List<BrowseByCategory>>
}