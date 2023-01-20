package com.example.Final_Project.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.Final_Project.domain.model.Receipt
import com.example.Final_Project.ui.MealRecipeApp


class ReceiptViewModel(application: Application) : AndroidViewModel(application) {

    private val getReceiptList = (application as MealRecipeApp).getReceiptUseCase()

    private val _mealsIdLiveData: MutableLiveData<String> = MutableLiveData()
    val mealsIdLiveData: LiveData<String>
        get() = _mealsIdLiveData

    val receiptResultLiveData: LiveData<com.example.Final_Project.domain.model.Result<List<Receipt>>> =
        Transformations.switchMap(_mealsIdLiveData) { mealsId ->
            val mealsListResponse = MutableLiveData<com.example.Final_Project.domain.model.Result<List<Receipt>>>()
            getReceiptList(mealsId) {
                mealsListResponse.value = it
            }
            mealsListResponse
        }

    fun loadMeals(mealsId: String) {
        _mealsIdLiveData.value = mealsId
    }

}