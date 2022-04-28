package com.bsuir.meditationapp.screen.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bsuir.meditationapp.BaseFragment
import com.bsuir.meditationapp.screen.main.MainActivity
import com.bsuir.meditationapp.R
import com.bsuir.meditationapp.databinding.FragmentRegisterBinding
import com.bsuir.meditationapp.db.ModelDb
import com.bsuir.meditationapp.model.User
import com.bsuir.meditationapp.repository.UserRepository
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment(R.layout.fragment_register) {

    private val binding: FragmentRegisterBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            val hasEmptyFields = binding.edittextName.text.isNullOrEmpty() ||
                    binding.edittextPassword.text.isNullOrEmpty()

            val hasInvalidEmail = !Patterns.EMAIL_ADDRESS
                .matcher(binding.edittextEmail.text.toString())
                .matches()

            if (hasEmptyFields || hasInvalidEmail) {
                binding.errorMessage.isVisible = true
            } else {
                lifecycleScope.launch {
                    UserRepository(ModelDb.getInstance(requireContext())).addUser(
                        User(
                            id = 0,
                            name = binding.edittextName.text.toString(),
                            email = binding.edittextEmail.text.toString(),
                            password = binding.edittextPassword.text.toString(),
                            isAuthenticated = true
                        )
                    )
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
                }
            }

        }
    }

}