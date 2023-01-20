package com.example.Final_Project.ui

import android.app.Application
import android.os.Handler
import android.os.Looper
import com.example.Final_Project.data.Api.retrofit.browseByCategory.BrowseByCategoryApiImpl
import com.example.Final_Project.data.Api.retrofit.browseByCategory.BrowseByCategoryRetrofitApi
import com.example.Final_Project.data.Api.retrofit.receipt.ReceiptRetrofitApi
import com.example.Final_Project.data.Api.retrofit.receipt.ReceiptRetrofitApiImpl
import com.example.Final_Project.data.BrowseByCategoryRepositoryImpl
import com.example.Final_Project.data.ReceiptRepositoryImpl
import com.example.Final_Project.domain.iteractor.GetBrowseByCategoryUseCase
import com.example.Final_Project.domain.iteractor.GetReceiptUseCase
import com.example.Final_Project.domain.repository.BrowseByCategoryRepository
import com.example.Final_Project.domain.repository.ReceiptRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MealRecipeApp : Application() {

    val browseByCategoryRepository: BrowseByCategoryRepository by lazy {
        BrowseByCategoryRepositoryImpl(
            BrowseByCategoryApiImpl(
                Retrofit.Builder()
                    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(BrowseByCategoryRetrofitApi::class.java)
            )
        )
    }

    fun getBrowseByCategoryUseCase(): GetBrowseByCategoryUseCase {
        return GetBrowseByCategoryUseCase(
            browseByCategoryRepository,
            executor,
            handler
        )
    }

    val receiptPageRepository: ReceiptRepository by lazy {
        ReceiptRepositoryImpl(
            ReceiptRetrofitApiImpl(
                Retrofit.Builder()
                    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ReceiptRetrofitApi::class.java)
            )
        )
    }

    fun getReceiptUseCase(): GetReceiptUseCase {
        return GetReceiptUseCase(
            receiptPageRepository,
            executor,
            handler
        )
    }

    private val executor: Executor by lazy {
        Executors.newCachedThreadPool()
    }
    private val handler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }

}