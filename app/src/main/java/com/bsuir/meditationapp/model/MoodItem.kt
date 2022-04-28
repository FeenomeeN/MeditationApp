package com.bsuir.meditationapp.model

import com.bsuir.meditationapp.R

enum class MoodItem(val textResId: Int, val iconResId: Int, val recommendationResId: Int) {
    CALM(R.string.calm, R.drawable.ic_calm, R.string.recommendation_calm),
    FOCUSED(R.string.focused, R.drawable.ic_focused, R.string.recommendation_focused),
    RELAXED(R.string.relaxed, R.drawable.ic_relaxed, R.string.recommendation_relaxed)
}