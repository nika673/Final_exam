package com.example.Final_Project.data.Api.retrofit.receipt

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ReceiptRetrofitApi {
    @GET("lookup.php")
    fun loadReceipt(@Query("i") meals: String): Call<ReceiptRetrofitResult>
}