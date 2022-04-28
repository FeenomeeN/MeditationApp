package com.bsuir.meditationapp.screen.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bsuir.meditationapp.BaseFragment
import com.bsuir.meditationapp.screen.main.MainActivity
import com.bsuir.meditationapp.R
import com.bsuir.meditationapp.databinding.FragmentLoginBinding
import com.bsuir.meditationapp.db.ModelDb
import com.bsuir.meditationapp.repository.UserRepository
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = ModelDb.getInstance(requireContext())
        val userRepository = UserRepository(db)

        binding.textRegister.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

        binding.signInButton.setOnClickListener {
            lifecycleScope.launch {
                userRepository.getAllUsers()
                    .firstOrNull {
                        it.email == binding.edittextEmail.text.toString() &&
                                it.password == binding.edittextPassword.text.toString()
                    }.let { user ->
                        if (user != null) {
                            userRepository.deauthAllUsers()
                            userRepository.addUser(user.copy(isAuthenticated = true))
                            startActivity(Intent(requireContext(), MainActivity::class.java))
                            requireActivity().finish()
                        } else {
                            binding.errorMessage.isVisible = true
                        }
                    }
            }
        }
    }
}
