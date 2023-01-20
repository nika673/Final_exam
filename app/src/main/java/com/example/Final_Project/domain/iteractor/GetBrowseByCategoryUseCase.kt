package com.example.Final_Project.domain.iteractor

import android.os.Handler
import com.example.Final_Project.domain.model.BrowseByCategory
import com.example.Final_Project.domain.repository.BrowseByCategoryRepository
import java.util.concurrent.Executor

class GetBrowseByCategoryUseCase(
    private val repository: BrowseByCategoryRepository,
    private val executor: Executor,
    private val handler: Handler
) {
    operator fun invoke(
        meals: String,
        callback: (mealsList: com.example.Final_Project.domain.model.Result<List<BrowseByCategory>>) -> Unit
    ) {
        return executor.execute {
            val mealsList = repository.getMealsList(meals)
            handler.post {
                callback(mealsList)
            }
        }
    }
}