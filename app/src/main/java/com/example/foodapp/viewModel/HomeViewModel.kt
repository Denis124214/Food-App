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
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) { // Этот метод вызывается, когда запрос завершен успешно. response содержит ответ от сервера  В данном случае, если тело ответа (response.body()) не равно null, то устанавливается значение mealDetailsLiveData с помощью первого блюда из списка meals
                if (response.body() != null) {
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal
                } else {
                    // Handle the case when response.body() == null
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) { // Этот метод вызывается в случае возникновения ошибки в процессе выполнения запроса
                Log.d("HomeFragment", t.message.toString())
            }
        })
    }
    fun randomObserveRandomMealLiveData () : LiveData<Meal> {
        return randomMealLiveData
    }
}