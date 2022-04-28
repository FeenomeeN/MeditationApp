package com.bsuir.meditationapp.screen.devinfo

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bsuir.meditationapp.R

class DevInfoActivity: AppCompatActivity(R.layout.activity_dev_info) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<ImageView>(R.id.close_button).setOnClickListener { finish() }
    }

}