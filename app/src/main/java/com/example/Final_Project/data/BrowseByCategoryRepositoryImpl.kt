package com.example.Final_Project.data

import com.example.Final_Project.data.Api.BrowseByCategoryApi
import com.example.Final_Project.domain.model.BrowseByCategory
import com.example.Final_Project.domain.repository.BrowseByCategoryRepository
import com.example.Final_Project.domain.model.Result

class BrowseByCategoryRepositoryImpl(
    private val browseByCategoryApi: BrowseByCategoryApi
) : BrowseByCategoryRepository {
    override fun getMealsList(meals: String): Result<List<BrowseByCategory>> {
        return try {
            Result.Success(browseByCategoryApi.fetchMeals(meals).map {
                BrowseByCategory(
                    it.strMeal,
                    it.strMealThumb,
                    it.idMeal
                )
            })
        } catch (e: ApiError) {
            Result.Error(e)
        }
    }
}