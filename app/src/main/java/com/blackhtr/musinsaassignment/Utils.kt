package com.blackhtr.musinsaassignment

import android.content.Context

object Utils {
    fun convertToPixel(context:Context, dp:Int):Int{
        val metrics = context.resources.displayMetrics
        return (dp * metrics.density).toInt()
    }
}