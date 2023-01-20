package com.example.Final_Project.domain.repository

import com.example.Final_Project.domain.model.Receipt
import com.example.Final_Project.domain.model.Result


interface ReceiptRepository {
    fun getReceipt(meals: String): Result<List<Receipt>>
}