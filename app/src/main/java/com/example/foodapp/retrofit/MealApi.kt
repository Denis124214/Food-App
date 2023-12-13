package com.example.foodapp.retrofit

import retrofit2.Call // Call в контексте библиотеки Retrofit представляет асинхронный запрос к веб-сервису. Он используется для обработки запросов к удаленному серверу и получения ответа
import com.example.foodapp.pojo.MealList
import retrofit2.http.GET

interface MealApi {
    @GET("random.php")
    fun getRandomMeal (): Call<MealList> // Она говорит о том, что функция не принимает аргументов (пустые скобки), и возвращает объект типа Call<MealList>.
}