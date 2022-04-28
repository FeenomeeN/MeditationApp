package com.bsuir.meditationapp.screen.main

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bsuir.meditationapp.R

class MarginItemDecoration: RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
//        if (parent.getChildAdapterPosition(view) != state.itemCount - 1) {
            outRect.bottom = parent.context.resources.getDimensionPixelSize(R.dimen.list_margin)
//        }
    }
}