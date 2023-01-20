package com.example.Final_Project.data


import com.example.Final_Project.data.Api.ReceiptPageApi
import com.example.Final_Project.domain.model.Receipt
import com.example.Final_Project.domain.repository.ReceiptRepository
import com.example.Final_Project.domain.model.Result



class ReceiptRepositoryImpl(
    private val receiptPageApi: ReceiptPageApi
) : ReceiptRepository {
    override fun getReceipt(meals: String): Result<List<Receipt>> {
        return try {
            Result.Success(receiptPageApi.fetchReceipt(meals).map {
                Receipt(
                    it.idMeal,
                    it.strMeal,
                    it.strMealThumb,
                    it.strInstructions,
                    it.strYoutube,

                    it.strIngredient1,
                    it.strIngredient2,
                    it.strIngredient3,
                    it.strIngredient4,
                    it.strIngredient5,
                    it.strIngredient6,
                    it.strIngredient7,
                    it.strIngredient8,
                    it.strIngredient9,
                    it.strIngredient10,
                    it.strIngredient11,
                    it.strIngredient12,
                    it.strIngredient13,
                    it.strIngredient14,
                    it.strIngredient15,
                    it.strIngredient16,
                    it.strIngredient17,
                    it.strIngredient18,
                    it.strIngredient19,
                    it.strIngredient20,

                    it.strMeasure1,
                    it.strMeasure2,
                    it.strMeasure3,
                    it.strMeasure4,
                    it.strMeasure5,
                    it.strMeasure6,
                    it.strMeasure7,
                    it.strMeasure8,
                    it.strMeasure9,
                    it.strMeasure10,
                    it.strMeasure11,
                    it.strMeasure12,
                    it.strMeasure13,
                    it.strMeasure14,
                    it.strMeasure15,
                    it.strMeasure16,
                    it.strMeasure17,
                    it.strMeasure18,
                    it.strMeasure19,
                    it.strMeasure20
                )
            })
        } catch (e: ApiError) {
            Result.Error(e)
        }
    }
}