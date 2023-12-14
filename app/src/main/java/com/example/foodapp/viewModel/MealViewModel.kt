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

class MealViewModel(): ViewModel() {
    private var mealDetailsLiveData = MutableLiveData<Meal>()

    fun getMealDetail(id: String) {
        RetrofitInstance.api.getMealDetails(id).enqueue(object : Callback<MealList> { // enqueue - это метод, который используется для выполнения асинхронных HTTP-запросов. Он предназначен для добавления запроса в очередь выполнения. Когда запрос завершается, вызываются соответствующие колбэки onResponse или onFailure. object : Callback<MealList>: Это создание анонимного объекта класса Callback<MealList>. Retrofit использует этот объект для обработки результатов запроса.
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) { //  Этот метод вызывается, когда запрос завершен успешно. response содержит ответ от сервера  В данном случае, если тело ответа (response.body()) не равно null, то устанавливается значение mealDetailsLiveData с помощью первого блюда из списка meals
                if (response.body() != null) {
                    mealDetailsLiveData.value = response.body()!!.meals[0]
                }
                else {
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) { // Этот метод вызывается в случае возникновения ошибки в процессе выполнения запроса
                Log.d("HomeFragment", t.message.toString())
            }

        })

    }
    fun observerMealDetailsLiveData(): LiveData<Meal> {
        return mealDetailsLiveData
    }
}