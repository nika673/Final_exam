package com.example.Final_Project.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.Final_Project.domain.model.BrowseByCategory
import com.example.Final_Project.ui.MealRecipeApp

class BrowseByCategoryViewModel(application: Application) : AndroidViewModel(application) {

    val getMealsList = (application as MealRecipeApp).getBrowseByCategoryUseCase()


    val _categoryNameLiveData: MutableLiveData<String> = MutableLiveData()
    val categoryNameLiveData: LiveData<String>
        get() = _categoryNameLiveData

    val browseByCategoryResultLiveData: LiveData<com.example.Final_Project.domain.model.Result<List<BrowseByCategory>>> =
        Transformations.switchMap(_categoryNameLiveData) { meal ->
            val categoryListResponse =
                MutableLiveData<com.example.Final_Project.domain.model.Result<List<BrowseByCategory>>>()
            getMealsList(meal) {
                categoryListResponse.value = it
            }
            categoryListResponse
        }

    fun loadMeals(meal: String) {
        _categoryNameLiveData.value = meal
    }
}