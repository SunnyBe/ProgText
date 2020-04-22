package com.zistus.progtext.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

/**
 * Build by ndusunday@gmail.com
 * To show progress with texts and a ball dropping on each text.
 */

class ProgText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {

    }


    // Handle Rendering contract between this component(ProgText) and the container i.e ViewGroup housing the (ProgText)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

}