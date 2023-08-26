package com.example.sisecevirmece.main
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator

// Function to modify and start the animation
fun modifyAndStartAnimation(view: View, toDegrees: Float):ObjectAnimator {
    val animation = ObjectAnimator.ofFloat(view, "rotation", 0f, toDegrees)
    animation.duration = 4000 // Set the duration (in milliseconds)
    animation.interpolator = DecelerateInterpolator() // Or any other interpolator if needed
    animation.start()
    return animation
}
