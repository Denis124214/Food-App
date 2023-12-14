package com.example.foodapp.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.databinding.ActivityMealBinding
import com.example.foodapp.fragments.HomeFragment
import com.example.foodapp.pojo.Meal
import com.example.foodapp.viewModel.MealViewModel

class MealActivity : AppCompatActivity() {
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var youtubeLink: String

    private lateinit var mealMvvm: MealViewModel

    lateinit var binding: ActivityMealBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMealBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mealMvvm = ViewModelProvider(this).get(MealViewModel::class.java)

        getMealInformationFromIntent() // // Этот метод предназначен для извлечения информации о блюде из Intent, чтобы вы могли использовать эти данные в дальнейшем в коде активности MealActivity
        setInformationInViews()

        loadingCase()
        mealMvvm.getMealDetail(mealId)
        observeMealDetailsLiveData()

        onYoutubeImageClick()
    }

    private fun onYoutubeImageClick() {
        binding.imageYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink)) //ACTION_VIEW с URI, система будет пытаться открыть подходящее приложение для просмотра веб-страницы с указанным URL. Uri.parse(youtubeLink) используется для преобразования строки youtubeLink в объект Uri. После этого объект Uri передается в Intent для указания местоположения или действия, которое должно быть выполнено при открытии этого Intent.
            startActivity(intent)
        }
    }

    private fun observeMealDetailsLiveData() {
        mealMvvm.observerMealDetailsLiveData().observe(this,object : Observer<Meal> { //  получает объект LiveData, который вы возвращаете из метода observerMealDetailsLiveData в вашем MealViewModel. .observe(this, object : Observer<Meal> - устанавливает наблюдателя (observer) для объекта LiveData. Когда данные в LiveData изменяются, вызывается метод onChanged вашего наблюдателя.
            override fun onChanged(t: Meal) { // метод onChanged, который вызывается при изменении данных в LiveData. Передается объект типа Meal.
                onResponseCase()
                val meal = t

                binding.tvCategory.text = "Categoty: ${meal!!.strCategory}" // обовляем данные
                binding.tvArea.text = "Area: ${meal!!.strArea}"
                binding.tvInstructionsSteps.text = meal.strInstructions

                youtubeLink = meal.strYoutube
            }

        })
    }
    private fun setInformationInViews () {
        Glide.with(applicationContext) // Android. Контекст (Context) в Android является базовым объектом, который предоставляет доступ к ресурсам, системным службам и другим основным функциям приложения.        Когда вы используете applicationContext, вы обращаетесь к контексту, который связан с всем приложением, а не с какой-то конкретной активностью или службой. Это означает, что вы можете использовать applicationContext в тех случаях, когда вам не нужен контекст, связанный с текущей активностью или службой, и вы хотите обратиться к глобальному контексту приложения. Ресурсы: layouts,strings,drawables
            .load(mealThumb)// загрузка
            .into(binding.imgMealDetail) // уустановка

        binding.colapsingToolbar.title = mealName
        binding.colapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white)) // цвет title в toolbar
        binding.colapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white)) // цвет title в img_meal_detail
    }

    private fun getMealInformationFromIntent() {
        val intent = intent // Этот объект intent содержит информацию, переданную из предыдущей активности (HomeFragment), в частности, значения, которые были добавлены как дополнительные данные (extras).
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!! // Извлекается строковое значение, связанное с ключом HomeFragment.MEAL_ID из Intent. Метод getStringExtra используется для получения значения строки. Поскольку getStringExtra может вернуть null, вы используете оператор !!, чтобы явно указать, что эти значения не могут быть null. Важно отметить, что использование !! предполагает, что данные всегда будут передаваться в Intent с этими ключами.
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }

    private fun loadingCase () { //  скрывает некоторые элементы пользовательского интерфейса и отображает индикатор загрузки, для того чтобы показать пользователю, что какой-то процесс выполняется.
        binding.progressBar.visibility = View.VISIBLE
        binding.btnAddToFav.visibility = View.INVISIBLE
        binding.tvInstructions.visibility = View.INVISIBLE
        binding.tvCategory.visibility = View.INVISIBLE
        binding.tvArea.visibility = View.INVISIBLE
        binding.imageYoutube.visibility = View.INVISIBLE
    }

    private fun onResponseCase () {  //скрывает некоторые элементы пользовательского интерфейса и отображает индикатор загрузки, для того чтобы показать пользователю, что какой-то процесс выполняется.
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnAddToFav.visibility = View.VISIBLE
        binding.tvInstructions.visibility = View.VISIBLE
        binding.tvCategory.visibility = View.VISIBLE
        binding.tvArea.visibility = View.VISIBLE
        binding.imageYoutube.visibility = View.VISIBLE
    }
}