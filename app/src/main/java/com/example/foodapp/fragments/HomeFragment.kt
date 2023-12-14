package com.example.foodapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.foodapp.databinding.FragmentHomeBinding
import com.bumptech.glide.Glide
import com.example.foodapp.activities.MealActivity
import com.example.foodapp.pojo.Meal
import com.example.foodapp.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewModel
    private lateinit var randomMeal: Meal

    companion object { // объект-компаньон, который является частью класса и может содержать статические члены, которые могут быть использованы без создания экземпляра класса. Эти константы могут быть использованы для стандартизации и упрощения кода при передаче данных между различными компонентами приложения, такими как фрагменты и активности. Например, при создании Intent для перехода на другой экран, эти константы могут использоваться для добавления дополнительных данных в Intent и их извлечения на другой стороне.
        const val MEAL_ID = "com.example.foodapp.fragments.idMeal"
        const val MEAL_NAME = "com.example.foodapp.fragments.nameMeal" // Константа, представляющая ключ для передачи названия блюда.
        const val MEAL_THUMB = "com.example.foodapp.fragments.thumbMeal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Move the Retrofit call inside onViewCreated
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeMvvm.getRandomMeal()
        observerRandomMeal()
        onRandomMealClick ()
    }

    private fun onRandomMealClick () {
        binding.randomMealCard.setOnClickListener {
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID,randomMeal.idMeal) // Intent.putExtra - это метод в классе Intent, который используется для добавления дополнительных данных (extras) к интенту. "Extra" в этом контексте означает дополнительную информацию или параметры, которые вы хотите передать из одной активности или фрагмента в другую.key: Это уникальный ключ, который идентифицирует дополнительные данные. Обычно это строковая константа, которая используется для извлечения данных из интента на другом конце.value: Это значение данных, которые вы хотите передать. Значение может быть различных типов данных, таких как строки, числа, булевы значения, объекты Parcelable и другие.
            intent.putExtra(MEAL_NAME,randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB,randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observerRandomMeal() {
        homeMvvm.randomObserveRandomMealLiveData().observe(viewLifecycleOwner, Observer { t -> // Здесь устанавливается наблюдатель за LiveData. Когда данные изменяются, блок кода внутри фигурных скобок будет выполнен.
            if (t != null) {
                Glide.with(this@HomeFragment)
                    .load(t.strMealThumb)
                    .into(binding.imgRandomMeal)

                this.randomMeal = t // t - Meal
            }
        })
    }

}
