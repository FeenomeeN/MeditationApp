package com.bsuir.meditationapp.screen.main

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bsuir.meditationapp.BaseFragment
import com.bsuir.meditationapp.R
import com.bsuir.meditationapp.databinding.FragmentMainScreenBinding
import com.bsuir.meditationapp.db.ModelDb
import com.bsuir.meditationapp.repository.HoroscopeRepository
import com.bsuir.meditationapp.repository.IUserRepository
import com.bsuir.meditationapp.repository.UserRepository
import com.bsuir.meditationapp.screen.devinfo.DevInfoActivity
import com.bsuir.meditationapp.util.provideHoroscopesApi
import com.bsuir.meditationapp.util.viewModelFactory
import com.example.feedthecat.screen.info.InfoActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.pow


class MainScreenFragment : BaseFragment(R.layout.fragment_main_screen) {

    private val binding: FragmentMainScreenBinding by viewBinding()

    private val horoscopeAdapter = HoroscopeAdapter()
    private val moodAdapter = MoodAdapter { showRecommendation(it) }

    private lateinit var sensorManager: SensorManager
    private lateinit var sensor: Sensor
    private lateinit var repository: IUserRepository

    val sensorListener = object : SensorEventListener {

        override fun onSensorChanged(event: SensorEvent?) {

        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = UserRepository(ModelDb.getInstance(requireContext()))

        sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)

        binding.recyclerHoroscope.apply {
            adapter = horoscopeAdapter
            addItemDecoration(MarginItemDecoration())
        }
        binding.recyclerMood.adapter = moodAdapter
        binding.menuButton.setOnClickListener { binding.root.openDrawer(GravityCompat.START) }
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.dev_info -> startActivity(
                    Intent(
                        requireContext(),
                        DevInfoActivity::class.java
                    )
                )
                R.id.app_info -> startActivity(Intent(requireContext(), InfoActivity::class.java))
                R.id.calc_imt -> printUserImt()
            }
            binding.root.close()
            true
        }

        lifecycleScope.launch {
            launch {
                binding.textWelcomeBack.text = resources.getString(R.string.welcome_back, repository.getAuthenticatedUser().name)
            }
            launch {
                horoscopeAdapter.setItems(HoroscopeRepository(provideHoroscopesApi()).getHoroscopes())
            }
        }
    }

    private fun printUserImt() {
        lifecycleScope.launch {
            val user = repository.getAuthenticatedUser()
            if (user.weight != -1 && user.height != -1) {
                val imt = user.weight / (user.height / 100).toDouble().pow(2)
                Toast.makeText(requireContext(), "Your imt: $imt", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Not enough info. Set it in your profile",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun showRecommendation(textResId: Int) {
        binding.textRecommendation.isVisible = true
        binding.textRecommendation.setText(textResId)
        binding.textRecommendation.setOnClickListener {
            findNavController().navigate(R.id.playerFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(sensorListener, sensor, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(sensorListener)
    }
}