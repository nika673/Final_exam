package com.example.Final_Project.domain.iteractor

import android.os.Handler
import com.example.Final_Project.domain.model.Receipt
import com.example.Final_Project.domain.repository.ReceiptRepository
import java.util.concurrent.Executor

class GetReceiptUseCase(
    private val repository: ReceiptRepository,
    private val executor: Executor,
    private val handler: Handler
) {

    operator fun invoke(
        meals: String,
        callback: (mealsList: com.example.Final_Project.domain.model.Result<List<Receipt>>) -> Unit
    ) {
        return executor.execute {
            val mealsList = repository.getReceipt(meals)
            handler.post {
                callback(mealsList)
            }
        }
    }
}