package com.bsuir.meditationapp.screen.player

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bsuir.meditationapp.BaseFragment
import com.bsuir.meditationapp.R
import com.bsuir.meditationapp.databinding.FragementPlayerBinding

class PlayerFragment: BaseFragment(R.layout.fragement_player) {

    private val binding: FragementPlayerBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mediaPlayer = MediaPlayer.create(requireContext(), R.raw.rickroll)

        binding.playButton.setOnClickListener {
            mediaPlayer.start()
            binding.playButton.isVisible = false
            binding.stopButton.isVisible = true
        }

        binding.stopButton.setOnClickListener {
            mediaPlayer.pause()
            binding.playButton.isVisible = true
            binding.stopButton.isVisible = false
        }
    }

}