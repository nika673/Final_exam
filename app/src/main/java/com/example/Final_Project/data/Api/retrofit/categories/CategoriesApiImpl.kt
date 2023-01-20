package com.example.Final_Project.data.Api.retrofit.categories

import com.example.Final_Project.data.Api.CategoriesApi
import com.example.Final_Project.data.ApiError
import com.example.Final_Project.data.model.CategoriesModel




class CategoriesApiImpl(private val categoriesRetrofitApi: CategoriesRetrofitApi) : CategoriesApi {
    override fun fetchCategory(category: String): List<CategoriesModel> {
        val call = categoriesRetrofitApi.loadCategory(category)
        val result = call.execute()

        if (result.isSuccessful) {
            return result.body()?.categories ?: emptyList()
        } else {
            throw ApiError(
                result.errorBody()?.string() ?: "api Error: responseCode=${result.code()}"
            )

        }

    }
}