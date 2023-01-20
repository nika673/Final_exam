package com.example.Final_Project.data.Api.retrofit.categories


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface CategoriesRetrofitApi {

    @GET("categories.php")
    fun loadCategory(@Query("categories") category: String): Call<CategoriesResult>
}