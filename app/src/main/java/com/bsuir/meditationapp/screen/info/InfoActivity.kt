package com.example.feedthecat.screen.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bsuir.meditationapp.R
import com.bsuir.meditationapp.databinding.ActivityInfoBinding

class InfoActivity: FragmentActivity(R.layout.activity_info) {

    companion object {
        private const val PAGE_COUNT = 3
    }

    private val binding: ActivityInfoBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.closeButton.setOnClickListener { finish() }
        binding.pager.apply {
            adapter = ScreenSlidePagerAdapter(this@InfoActivity)
        }
    }

    override fun onBackPressed() {
        val pager = binding.pager
        if (pager.currentItem == 0) {
            super.onBackPressed()
        } else {
            pager.currentItem = pager.currentItem - 1
        }
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = PAGE_COUNT

        override fun createFragment(position: Int): Fragment = when(position) {
            0 -> InfoFragmentOne()
            1 -> InfoFragmentTwo()
            2 -> InfoFragmentThree()
            else -> throw IllegalStateException()
        }
    }
}