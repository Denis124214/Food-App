package com.example.foodapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.databinding.ActivityMainBinding
import com.example.foodapp.databinding.ActivityMealBinding
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.fragments.HomeFragment

class MealActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setInformationInViews ()
    }


//    private fun setInformationInViews () {
//        Glide.with(applicationContext)
//            .load(mealThumb)
//            .into(binding.imgMealDetail)
//
//        binding.colapsingToolbar.title = mealName
//    }
}