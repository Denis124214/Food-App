package com.example.foodapp.retrofit

import retrofit2.Call // Call в контексте библиотеки Retrofit представляет асинхронный запрос к веб-сервису. Он используется для обработки запросов к удаленному серверу и получения ответа
import com.example.foodapp.pojo.MealList
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("random.php")
    fun getRandomMeal (): Call<MealList> // Она говорит о том, что функция не принимает аргументов (пустые скобки), и возвращает объект типа Call<MealList>.
    @GET("lookup.php?")
    fun getMealDetails(@Query("i") id:String) : Call<MealList> // @Query("i") id: String: Эта аннотация указывает Retrofit на то, что параметр id должен быть добавлен в URL запроса как query-параметр. Здесь "i" - это имя query-параметра, и id - это значение, которое будет передано в запросе.
}