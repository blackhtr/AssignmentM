package com.blackhtr.musinsaassignment.layout

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.blackhtr.musinsaassignment.R
import com.blackhtr.musinsaassignment.data.DataDTO
import kotlinx.android.synthetic.main.item_footer.view.*
import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.android.synthetic.main.item_main_holder.view.*

class MainAdapter(context:Context): RecyclerView.Adapter<ViewHolder>() {
    private val mContext = context
    private var mData:MutableList<DataDTO> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data : List<DataDTO>?){
        mData.clear()
        data?.run { mData.addAll(this) }
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = mData.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  = MainViewHolder(parent).apply { setClickListener(this) }
    private fun setClickListener(holder:MainViewHolder){

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(holder is MainViewHolder && 0 <= position && position < mData.size){
            MainHolderManager.setHolderData(mContext, holder, mData[position])
        }
    }
}

class MainViewHolder(parent: ViewGroup) : ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main_holder, parent, false)){
    // HEADER
    val inHeader: View = itemView.inHeader
    val ivHeaderIcon:ImageView = itemView.inHeader.ivHeaderIcon
    val tvHeaderTitle:TextView = itemView.inHeader.tvHeaderTitle
    val tvHeaderLink:TextView = itemView.inHeader.tvHeaderLink
    // CONTENTS
    val rvContents:RecyclerView = itemView.rvContents
    // FOOTER
    val inFooter: View = itemView.inFooter
    val ivFooterIcon:ImageView = itemView.inFooter.ivFooterIcon
    val tvFooterTitle:TextView = itemView.inFooter.tvFooterTitle
}