package com.example.foodapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.pojo.Meal
import com.example.foodapp.pojo.MealList
import com.example.foodapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel (): ViewModel() {
    private var randomMealLiveData = MutableLiveData<Meal>()
    fun getRandomMeal () {
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList> { // enqueue - это метод, который используется для выполнения асинхронных HTTP-запросов. Он предназначен для добавления запроса в очередь выполнения. Когда запрос завершается, вызываются соответствующие колбэки onResponse или onFailure
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null) {
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal
                } else {
                    // Handle the case when response.body() == null
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }
        })
    }
    fun randomObserveRandomMealLiveData () : LiveData<Meal> {
        return randomMealLiveData
    }
}