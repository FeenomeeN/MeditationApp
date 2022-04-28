package com.bsuir.meditationapp.screen.profile

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bsuir.meditationapp.BaseFragment
import com.bsuir.meditationapp.R
import com.bsuir.meditationapp.databinding.FragmentProfileBinding
import com.bsuir.meditationapp.db.ModelDb
import com.bsuir.meditationapp.model.User
import com.bsuir.meditationapp.repository.IUserRepository
import com.bsuir.meditationapp.repository.UserRepository
import com.bsuir.meditationapp.screen.login.LoginActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.File

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding()

    private val pickImages =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let { _ ->
                binding.profilePicture.setImageURI(uri)
                saveBitmapToInternalStorage(uri)
            }
        }

    private lateinit var repository: IUserRepository

    private val user = MutableStateFlow<User?>(null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = UserRepository(ModelDb.getInstance(requireContext()))

        binding.textExit.setOnClickListener {
            lifecycleScope.launch {
                user.value?.copy(isAuthenticated = false)?.let { repository.addUser(it) }
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                requireActivity().finish()
            }
        }

        lifecycleScope.launch {
            user.emit(repository.getAuthenticatedUser())
            loadUserImage()
        }

        user.onEach {
            it?.let { user ->
                binding.userName.text = user.name
                binding.userEmail.text = user.email
                if (user.height != -1) {
                    binding.edittextHeight.setText(user.height.toString())
                }
                if (user.weight != -1) {
                    binding.edittextWeight.setText(user.weight.toString())
                }
            }

        }.launchIn(lifecycleScope)

        binding.profilePicture.setOnClickListener { pickImages.launch("image/*") }
    }

    override fun onPause() {
        super.onPause()
        lifecycleScope.launch {
            if (binding.edittextWeight.text.isNotEmpty() || binding.edittextHeight.text.isNotEmpty()) {
                user.value?.copy(
                    weight = Integer.parseInt(binding.edittextWeight.text.toString()),
                    height = Integer.parseInt(binding.edittextHeight.text.toString())
                )?.let {
                    repository.addUser(it)
                }
            }
        }
    }

    private fun getUserImageFileName() = "${user.value?.id}_USER_IMAGE"

    private fun saveBitmapToInternalStorage(uri: Uri) {
        val descriptor = requireActivity()
            .contentResolver
            .openFileDescriptor(uri, "r")
            ?.fileDescriptor

        val bitmap = BitmapFactory.decodeFileDescriptor(descriptor)

        requireContext().openFileOutput(getUserImageFileName(), Context.MODE_PRIVATE).use { fos ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        }
    }

    private fun loadUserImage() {
        if (userHasSavedImage()) {
            binding.profilePicture.setImageBitmap(getUserImageBitmap())
        } else {
            binding.profilePicture.setImageResource(R.drawable.ic_question)
        }
    }

    private fun getUserImageBitmap(): Bitmap {
        val inputStream = requireContext().openFileInput(getUserImageFileName())?.buffered()
        return BitmapFactory.decodeStream(inputStream)
    }

    private fun userHasSavedImage() =
        File(requireContext().filesDir, getUserImageFileName()).exists()

}
