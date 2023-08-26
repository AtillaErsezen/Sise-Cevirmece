// CircularLayout.kt
package com.example.sisecevirmece.main

import android.content.Context
import android.graphics.Canvas
import android.graphics.PointF
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children
import com.example.sisecevirmece.R
import kotlin.math.cos
import kotlin.math.sin

class CircularLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    // Center point of the circular layout
    private val centerPoint = PointF()

    // The radius of the circular layout (default value: 300 pixels)
    private val radius: Float

    // Initialization block
    init {
        // Obtain the custom attributes (if any) from the layout XML file
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CircularLayout)

        // Get the radius attribute from the XML or use the default value (300 pixels)
        radius = attributes.getDimension(R.styleable.CircularLayout_radius, 300f)

        // Recycle the attributes to free up resources
        attributes.recycle()
    }

    // onMeasure is called to measure the size of the CircularLayout and its children
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // Calculate the center point of the CircularLayout
        centerPoint.x = measuredWidth.toFloat() / 2
        centerPoint.y = measuredHeight.toFloat() / 2

        // Measure and layout child views (measuring is done by their own onMeasure)
        for (child in children) {
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
        }
    }

    // onLayout is called to position the children within the CircularLayout
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        // Get the number of child views in the CircularLayout
        val childCount = childCount
        val totalAngle = 360f

        // Calculate the angle between each child view
        val childAngle = totalAngle / childCount

        // Loop through each child view to position them around the circle
        for (i in 0 until childCount) {
            val child = getChildAt(i)

            // Calculate the angle in radians for the current child
            val childAngleRadians = Math.toRadians(childAngle * i.toDouble())

            // Calculate the X and Y coordinates of the child view
            val childX = centerPoint.x + radius * cos(childAngleRadians).toFloat()
            val childY = centerPoint.y + radius * sin(childAngleRadians).toFloat()

            // Adjust the child view position to consider its center as the pivot point
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight
            val childLeft = (childX - childWidth / 2).toInt()
            val childTop = (childY - childHeight / 2).toInt()
            val childRight = childLeft + childWidth
            val childBottom = childTop + childHeight

            // Position the child view within the CircularLayout
            child.layout(childLeft, childTop, childRight, childBottom)

            // Calculate the rotation angle to make textviews readable from each player's perspective
            // Since the textviews are rotated inwards by default, we subtract 90 degrees to make them face outwards
            val rotationAngle = childAngle * i - 90

            // Rotate the child view to the proper angle around the center point
            child.pivotX = childWidth / 2f
            child.pivotY = childHeight / 2f
            child.rotation = rotationAngle
        }
    }

    // onDraw is called to draw custom content on the CircularLayout (if needed)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // If you need to draw something specific on the circular layout, you can do it here.
        // This method will be called after the child views have been drawn.
    }
}
