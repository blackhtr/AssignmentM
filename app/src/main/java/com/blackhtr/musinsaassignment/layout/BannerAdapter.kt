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
import com.blackhtr.musinsaassignment.Glide.GlideApp
import com.blackhtr.musinsaassignment.R
import com.blackhtr.musinsaassignment.data.BannerDTO
import com.blackhtr.musinsaassignment.data.DataDTO
import kotlinx.android.synthetic.main.item_footer.view.*
import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.android.synthetic.main.item_main_holder.view.*

class BannerAdapter(context:Context): RecyclerView.Adapter<ViewHolder>() {
    private val mContext = context
    private var mData:MutableList<BannerDTO> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data : List<BannerDTO>?){
        mData.clear()
        data?.run { mData.addAll(this) }
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = mData.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  = BannerViewHolder(parent).apply { setClickListener(this) }
    private fun setClickListener(holder:BannerViewHolder){

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(holder is BannerViewHolder && 0 <= position && position < mData.size){
            val bannerData = mData[position]
            if(!bannerData.thumbnailURL.isNullOrBlank()) GlideApp.with(mContext).load(bannerData.thumbnailURL).into(holder.ivBanner)
        }
    }
}

class BannerViewHolder(parent: ViewGroup) : ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_banner_holder, parent, false)){
    val ivBanner:ImageView = itemView.findViewById(R.id.ivBanner)
}