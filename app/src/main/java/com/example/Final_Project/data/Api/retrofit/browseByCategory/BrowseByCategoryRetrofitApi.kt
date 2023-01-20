package com.example.Final_Project.data.Api.retrofit.browseByCategory

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BrowseByCategoryRetrofitApi {
    @GET("filter.php")
    fun loadMeals(@Query("c") meals: String): Call<BrowseByCategoryResult>
}