package com.blackhtr.musinsaassignment

import android.content.Context

object Utils {

    fun getDeviceWidthDp(context:Context):Float{
        val metrics = context.resources.displayMetrics
        val pxWidth = metrics.widthPixels
        return pxWidth / (metrics.densityDpi / 160f)
    }
    fun getDeviceWidthPixel(context:Context):Int{
        val metrics = context.resources.displayMetrics
        return metrics.widthPixels
    }
}