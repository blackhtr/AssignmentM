package com.blackhtr.musinsaassignment.layout

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.blackhtr.musinsaassignment.Utils

abstract class BaseAdapter(context: Context, type:String): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val contentsType = type
    var totalCount:Int = 0
    var showLine = 2
    var lineCount:Int = when(type){
        MainHolderManager.TYPE_GRID -> 3
        MainHolderManager.TYPE_STYLE -> 2
        else -> 1
    }
    private val displayWidth:Int = getDeviceWidthPixel(context)
    val itemWidth:Int = when(type){
        MainHolderManager.TYPE_GRID, MainHolderManager.TYPE_STYLE -> getItemWidth(context, lineCount)
        MainHolderManager.TYPE_SCROLL -> displayWidth/3
        //MainHolderManager.TYPE_BANNER,
        else -> displayWidth
    }

    abstract fun refreshData()

    @SuppressLint("NotifyDataSetChanged")
    fun moreData(){
        showLine++
        notifyDataSetChanged()
    }

    private fun getItemWidth(context: Context, itemCnt:Int):Int{
        val pxMargin = Utils.convertToPixel(context, CustomDecoration.DECORATION_MARGIN)
        val pxSpace = Utils.convertToPixel(context, CustomDecoration.DECORATION_SPACE)
        return (displayWidth - (pxMargin * 2) - (pxSpace * (itemCnt-1))) / itemCnt
    }

    private fun getDeviceWidthPixel(context:Context):Int{
        val metrics = context.resources.displayMetrics
        return metrics.widthPixels
    }
}