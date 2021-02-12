package com.hiral.demotest.utils

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hiral.demotest.ui.dataRecyclerView.DataRecyclerView
import java.text.FieldPosition

class CenterView : LinearLayoutManager {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(
            context,
            orientation,
            reverseLayout
    )

    constructor(dataRecyclerView: DataRecyclerView, position: Int) : super(dataRecyclerView)

    override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {

        lp!!.width =
            (width/1.5f).toInt()    //width of each item will be width of recyclerview div by 2
        return true
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        scaleMiddleItem()
    }

    override fun scrollHorizontallyBy(
            dx: Int,
            recycler: RecyclerView.Recycler?,
            state: RecyclerView.State?
    ): Int {
        return if (orientation == HORIZONTAL) {
            val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
            scaleMiddleItem()
            scrolled
        } else {
            0
        }
    }

    private fun scaleMiddleItem() {
        val mid = width / 2
        val d1 = 1.0f * mid
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childMid = (getDecoratedRight(child!!) + getDecoratedLeft(child!!)) / 2f
            val d = Math.min(d1, Math.abs(mid - childMid))
            val scale = 1 - 0.15f * d / d1
            child.scaleX = scale
            child.scaleY = scale
        }
    }


}