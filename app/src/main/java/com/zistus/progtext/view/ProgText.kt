package com.zistus.progtext.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.zistus.progtext.R
import com.zistus.progtext.util.Default

/**
 * Build by ndusunday@gmail.com
 * To show progress with texts and a ball dropping on each text.
 */

class ProgText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var text = "Please Wait"
    var textSize: Float
    var size: Int
    val colorInt: Int
    val textSpacing: Float

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create("", Typeface.BOLD)
    }

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    //Point to draw the loading circle
    private val loadingCirclePosition: PointF = PointF(0.0f, 0.0f)
    private var incrementBallPosition: Float = 0f
    private var ballColor = Color.RED

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgText)
        text = typedArray.getString(R.styleable.ProgText_text) ?: Default.text
        textSize = typedArray.getDimension(R.styleable.ProgText_textSize, 40f)
        size = typedArray.getDimensionPixelSize(R.styleable.ProgText_progTextSize, width)
        colorInt = typedArray.getColor(
            R.styleable.ProgText_textColor,
            resources.getColor(R.color.colorAccent)
        )
        textSpacing = typedArray.getInt(R.styleable.ProgText_textSpacing, 0).toFloat()

        isClickable = true
    }

    // Handle Rendering contract between this component(ProgText) and the container i.e ViewGroup housing the (ProgText)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        textPaint.color = colorInt
        textPaint.textSize = textSize
        textPaint.letterSpacing = textSpacing
        canvas?.drawText(text, (size) * 1.0f, (size) * 1.0f, textPaint)

        circlePaint.color = ballColor
        loadingCirclePosition.calculateCircleCoordinate(size.toFloat())
//        canvas?.drawCircle(size*0.5f, size*0.8f, 25f, circlePaint)
        canvas?.drawCircle(loadingCirclePosition.x, loadingCirclePosition.y, 20f, circlePaint)
    }

    private fun moveBallOnChars() {
        incrementBallPosition = 0.5f
        ballColor = Color.GREEN
    }

    private fun PointF.calculateCircleCoordinate(size: Float) {
        x = (size * 0.5f) + incrementBallPosition
        y = (size * 0.8f) + incrementBallPosition
    }

    override fun performClick(): Boolean {
        if (super.performClick()) return true
        // Update the coordinate of ball and redraw
        moveBallOnChars()
        invalidate()
        return true
    }

}