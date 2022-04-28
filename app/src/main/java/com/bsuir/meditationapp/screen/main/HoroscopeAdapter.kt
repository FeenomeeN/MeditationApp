package com.bsuir.meditationapp.screen.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bsuir.meditationapp.R
import com.bsuir.meditationapp.databinding.HoroscopeListItemBinding
import com.bsuir.meditationapp.model.Horoscope

class HoroscopeAdapter: RecyclerView.Adapter<HoroscopeAdapter.HoroscopeViewHolder>() {

    private var horoscopeItems: List<Horoscope> = emptyList()

    inner class HoroscopeViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding: HoroscopeListItemBinding by viewBinding()

        fun bind(horoscope: Horoscope) {
            binding.sign.text = horoscope.sign
            binding.text.text = horoscope.horoscope

            binding.moreButton.setOnClickListener {
                binding.text.maxLines = Integer.MAX_VALUE
                binding.moreButton.isVisible = false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.horoscope_list_item, parent, false)
        return HoroscopeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        holder.bind(horoscopeItems[position])
    }

    override fun getItemCount() = horoscopeItems.size

    fun setItems(horoscopeList: List<Horoscope>) {
        horoscopeItems = horoscopeList
        notifyDataSetChanged()
    }

}