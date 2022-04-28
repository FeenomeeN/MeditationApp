package com.bsuir.meditationapp.screen.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bsuir.meditationapp.BaseFragment
import com.bsuir.meditationapp.R
import com.bsuir.meditationapp.databinding.FragmentWelcomeBinding

class WelcomeFragment: BaseFragment(R.layout.fragment_welcome) {

    private val binding: FragmentWelcomeBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.enterAccountButton.setOnClickListener { navController.navigate(R.id.loginFragment) }
        binding.textNoAccount.setOnClickListener { navController.navigate(R.id.registerFragment) }

    }

}