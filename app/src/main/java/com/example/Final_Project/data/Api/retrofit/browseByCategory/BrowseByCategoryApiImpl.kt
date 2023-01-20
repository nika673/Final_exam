package com.example.Final_Project.data.Api.retrofit.browseByCategory

import com.example.Final_Project.data.Api.BrowseByCategoryApi
import com.example.Final_Project.data.ApiError
import com.example.Final_Project.data.model.BrowseByCategoryModel


class BrowseByCategoryApiImpl(private val browseByCategoryRetrofitApi: BrowseByCategoryRetrofitApi) :
    BrowseByCategoryApi {

    override fun fetchMeals(meals: String): List<BrowseByCategoryModel> {
        val call = browseByCategoryRetrofitApi.loadMeals(meals)
        val result = call.execute()

        if (result.isSuccessful) {
            return result.body()?.meals ?: emptyList()
        } else {
            throw ApiError(
                result.errorBody()?.string() ?: "api Error: responseCode=${result.code()}"
            )

        }
    }

}