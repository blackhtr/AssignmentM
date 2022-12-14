package com.blackhtr.musinsaassignment.layout

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.blackhtr.musinsaassignment.R
import com.blackhtr.musinsaassignment.data.DataDTO
import com.blackhtr.musinsaassignment.data.FooterDTO

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
        holder.tvHeaderLink.setOnClickListener {
            val position = holder.adapterPosition
            if(RecyclerView.NO_POSITION != position && position < mData.size){
                val headerLinkUrl = mData[position].header?.linkURL?:""
                if(headerLinkUrl.isNotBlank()) mContext.startActivity(Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(headerLinkUrl) })
            }
        }
        holder.inFooter.setOnClickListener {
            val position = holder.adapterPosition
            if(RecyclerView.NO_POSITION != position && position < mData.size){
                val footerData:FooterDTO? = mData[position].footer
                when(footerData?.type){
                    MainHolderManager.TYPE_FOOTER_MORE -> {
                        holder.rvContents.adapter?.run { if(this is BaseAdapter){
                            moreData()
                            if(totalCount == itemCount) holder.inFooter.visibility = View.GONE
                        }}
                    }
                    MainHolderManager.TYPE_FOOTER_REFRESH -> {
                        holder.rvContents.adapter?.run { if(this is BaseAdapter) this.refreshData() }
                    }
                }
            }
        }

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(holder is MainViewHolder && 0 <= position && position < mData.size){
            MainHolderManager.setHolderData(mContext, holder, mData[position])
        }
    }
}

class MainViewHolder(parent: ViewGroup) : ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main_holder, parent, false)){
    // HEADER
    val inHeader: View = itemView.findViewById(R.id.inHeader)
    val ivHeaderIcon:ImageView = inHeader.findViewById(R.id.ivHeaderIcon)
    val tvHeaderTitle:TextView = inHeader.findViewById(R.id.tvHeaderTitle)
    val tvHeaderLink:TextView = inHeader.findViewById(R.id.tvHeaderLink)
    // CONTENTS
    val rvContents:RecyclerView = itemView.findViewById(R.id.rvContents)
    // FOOTER
    val inFooter: View = itemView.findViewById(R.id.inFooter)
    val ivFooterIcon:ImageView = inFooter.findViewById(R.id.ivFooterIcon)
    val tvFooterTitle:TextView = inFooter.findViewById(R.id.tvFooterTitle)
}