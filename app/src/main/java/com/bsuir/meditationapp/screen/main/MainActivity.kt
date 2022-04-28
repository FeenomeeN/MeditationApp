package com.bsuir.meditationapp.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bsuir.meditationapp.R
import com.bsuir.meditationapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.mainBottomButton.setOnClickListener {
            Navigation.findNavController(this, R.id.nav_host_fragment)
                .navigate(R.id.mainScreenFragment)
        }

        binding.playerBottomButton.setOnClickListener {
            Navigation.findNavController(this, R.id.nav_host_fragment)
                .navigate(R.id.playerFragment)
        }

        binding.profileBottomButton.setOnClickListener {
            Navigation.findNavController(this, R.id.nav_host_fragment)
                .navigate(R.id.profileFragment)
        }

    }
}