package com.bsuir.meditationapp.screen.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bsuir.meditationapp.R
import com.bsuir.meditationapp.databinding.MoodListItemBinding
import com.bsuir.meditationapp.model.MoodItem

class MoodAdapter(private val onMoodClicked: (Int) -> Unit) :
    RecyclerView.Adapter<MoodAdapter.MoodViewHolder>() {

    private val items = MoodItem.values()

    inner class MoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding: MoodListItemBinding by viewBinding()

        fun bind(moodItem: MoodItem) {
            binding.icon.setImageResource(moodItem.iconResId)
            binding.text.setText(moodItem.textResId)
            binding.root.setOnClickListener { onMoodClicked(moodItem.recommendationResId) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.mood_list_item, parent, false)
        return MoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoodViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}