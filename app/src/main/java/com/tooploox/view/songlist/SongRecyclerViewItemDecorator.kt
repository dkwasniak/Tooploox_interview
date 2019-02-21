package com.tooploox.view.songlist

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tooploox.R

class SongRecyclerViewItemDecorator(context: Context) : RecyclerView.ItemDecoration() {

    private val spacing = context.resources.getDimensionPixelSize(R.dimen.material_baseline_grid_0_5x)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        setMargins(outRect, spacing, spacing, 2 * spacing, 2 * spacing)
    }

    private fun setMargins(outRect: Rect, top: Int, bottom: Int, left: Int, right: Int) {
        outRect.top = top
        outRect.bottom = bottom
        outRect.left = left
        outRect.right = right
    }
}