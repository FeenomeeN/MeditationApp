package com.example.feedthecat.screen.info

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bsuir.meditationapp.R

abstract class InfoFragment: Fragment(R.layout.fragment_info) {

    protected abstract val textResId: Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.text).setText(textResId)
    }

}