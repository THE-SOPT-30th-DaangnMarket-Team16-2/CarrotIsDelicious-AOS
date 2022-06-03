package org.sopt.carrot16_2.util

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import org.sopt.carrot16_2.R

class ItemDecoration(private val lineColor : String) : RecyclerView.ItemDecoration() {


    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val paint = Paint().apply {
            color = Color.parseColor(lineColor)
            this.style = Paint.Style.STROKE
        }

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val top = (child.bottom + layoutParams.bottomMargin).toFloat()
            val bottom = top + 1
            c.drawRect(child.left.toFloat(), top, child.right.toFloat(), bottom, paint)
        }
    }
}
