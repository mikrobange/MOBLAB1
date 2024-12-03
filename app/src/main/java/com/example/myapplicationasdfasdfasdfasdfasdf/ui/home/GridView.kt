package com.example.myapplicationasdfasdfasdfasdfasdf.ui.home

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.myapplicationasdfasdfasdfasdfasdf.SIGNALS.Matavimas

class GridView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val gridPaint = Paint().apply {
        color = 0xFFAAAAAA.toInt() // Light gray color for the grid lines
        strokeWidth = 2f
    }
    private val textPaint = Paint().apply {
        color = Color.BLACK
        textSize = 25f
        textAlign = Paint.Align.CENTER
    }
    private val squarePaint = Paint().apply {
        color = Color.BLUE // Color for the squares
    }
    private val squarePaint2 = Paint().apply {
        color = Color.RED // Color for the squares
    }
    var sig: List<Matavimas> = emptyList()
    var usr: List<Matavimas> = emptyList()
    private val gridSize = 40f // Set grid cell size
    var coordinates: List<Pair<Int, Int>> = emptyList() // List of x, y coordinate pairs
    var color = false
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat() - 500f

//        // Draw vertical lines for the grid from -10 to 10 horizontally
//        for (i in -5..6) {
//            val x = i * gridSize + width / 2 // Centering the grid horizontally
//            canvas.drawLine(x, 0f, x, height, paint)
//        }
//
//        // Draw horizontal lines for the grid from -20 to 20 vertically
//        for (i in -11-16..19 -15) {
//            val y = height / 2 - i * gridSize // Centering the grid vertically
//            canvas.drawLine(0f, y, width, y, paint)
//        }
        val centerX = width / 2
        val centerY = height / 2

        // Draw vertical grid lines and x-axis labels
        for (i in -5..7) {  // Adjust the range as needed for the visible grid
            val x = i * gridSize + centerX
            canvas.drawLine(x, 0f, x, height*2, gridPaint)
            if (i != 7)
                canvas.drawText(i.toString(), x + gridSize / 2,  height +300f , textPaint) // Centered horizontally in the grid
        }

        // Draw horizontal grid lines and y-axis labels
        for (i in -12..35) { // Adjust the range as needed
            val y = centerY + (15 - i) * gridSize
            canvas.drawLine(0f, y, width, y, gridPaint)
            if (i != -12) { // Avoid overlapping at the origin
                canvas.drawText((i).toString(),  500f, y + gridSize / 2 + 10f, textPaint) // Centered vertically in the grid
            }
        }

            sig.forEach { matavimas ->
                // Adjust x and y based on the offsets
                val x = (matavimas.x.toFloat()) * gridSize + (width / 2) + 5f
                val y = (height / 2) +(15 - matavimas.y.toFloat()) * gridSize +5f
                canvas.drawRect(x, y, x + gridSize - 10f, y + gridSize -10f, squarePaint)
            }
            usr.forEach { matavimas ->
                // Adjust x and y based on the offsets
                val x = (matavimas.x.toFloat()) * gridSize + (width / 2) + 5f
                val y = (height / 2) +(15 - matavimas.y.toFloat()) * gridSize +5f
                canvas.drawRect(x, y, x + gridSize - 10f, y + gridSize -10f, squarePaint2)
            }


//        // Draw colored squares for each signal
//        sig.forEach { matavimas ->
//            // Adjust x and y based on the offsets
//            val x = (matavimas.x.toFloat()) * gridSize + (width / 2) + 5f
//            val y = (height / 2) +(15 - matavimas.y.toFloat()) * gridSize +5f
//            canvas.drawRect(x, y, x + gridSize - 10f, y + gridSize -10f, squarePaint)
//        }
//        usr.forEach { matavimas ->
//            // Adjust x and y based on the offsets
//            val x = (matavimas.x.toFloat()) * gridSize + (width / 2) + 5f
//            val y = (height / 2) +(15 - matavimas.y.toFloat()) * gridSize +5f
//            canvas.drawRect(x, y, x + gridSize - 10f, y + gridSize -10f, squarePaint2)
//        }


    }

    fun Chng() {
        color = !color
    }
    // Method to set signals and invalidate the view
    fun setSignals(signals: List<Matavimas>) {
        this.sig = signals
        invalidate() // Trigger a redraw
    }
    fun setUsers(signals: List<Matavimas>) {
        this.usr = signals
        invalidate() // Trigger a redraw
    }

}
