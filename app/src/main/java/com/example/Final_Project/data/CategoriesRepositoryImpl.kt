package com.example.Final_Project.data


import android.os.Handler
import com.example.Final_Project.data.Api.CategoriesApi
import com.example.Final_Project.data.model.CategoriesModel
import com.example.Final_Project.domain.model.Result
import com.example.Final_Project.domain.repository.CategoriesRepository


import java.util.concurrent.Executor

class CategoriesRepositoryImpl(
    private val executor: Executor,
    private val handler: Handler,
    private val categoriesApi: CategoriesApi
) : CategoriesRepository {
    override fun getCategoryList(
        category: String,
        callback: (categoryList: Result<List<CategoriesModel>>) -> Unit
    ) {
        executor.execute {
            val categoryList = try {
                Result.Success(categoriesApi.fetchCategory(category))
            } catch (e: ApiError) {
                Result.Error(e)
            }
            handler.post {
                callback(categoryList)
            }
        }
    }
}