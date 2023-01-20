package com.example.Final_Project.data.Api.retrofit.receipt

import com.example.Final_Project.data.Api.ReceiptPageApi
import com.example.Final_Project.data.ApiError
import com.example.Final_Project.data.model.ReceiptPageModel


class ReceiptRetrofitApiImpl(private val receiptRetrofitApi: ReceiptRetrofitApi) : ReceiptPageApi {

    override fun fetchReceipt(meals: String): List<ReceiptPageModel> {
        val call = receiptRetrofitApi.loadReceipt(meals)
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