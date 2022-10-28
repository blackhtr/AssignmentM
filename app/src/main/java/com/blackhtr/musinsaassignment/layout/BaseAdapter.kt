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
        else -> 0
    }
    val displayWidth:Int = Utils.getDeviceWidthPixel(context)

    abstract fun refreshData()

    @SuppressLint("NotifyDataSetChanged")
    fun moreData(){
        showLine++
        notifyDataSetChanged()
    }
}