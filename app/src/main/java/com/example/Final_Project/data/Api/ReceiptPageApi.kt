package com.example.Final_Project.data.Api

import com.example.Final_Project.data.model.ReceiptPageModel

interface ReceiptPageApi {
    fun fetchReceipt(meals: String): List<ReceiptPageModel>
}