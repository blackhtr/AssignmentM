package com.blackhtr.musinsaassignment.layout

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.blackhtr.musinsaassignment.Glide.GlideApp
import com.blackhtr.musinsaassignment.R
import com.blackhtr.musinsaassignment.data.BannerDTO

class BannerAdapter(context:Context, type:String, recyclerView: RecyclerView): BaseAdapter(context, type){
    private val mContext = context
    private var mData:MutableList<BannerDTO> = mutableListOf()
    private val mRecyclerView = recyclerView
    private val mSnapHelper = PagerSnapHelper()

    override fun refreshData() {}

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data : List<BannerDTO>?){
        mData.clear()
        data?.run { mData.addAll(this) }
        totalCount = mData.size
        mSnapHelper.attachToRecyclerView(null)
        mSnapHelper.attachToRecyclerView(mRecyclerView)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = Int.MAX_VALUE
    fun getRealItemCount():Int = mData.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  = BannerViewHolder(parent, itemWidth).apply { setClickListener(this) }
    private fun setClickListener(holder:BannerViewHolder){
        holder.itemView.setOnClickListener {
            val realPosition =  holder.adapterPosition % mData.size
            if(RecyclerView.NO_POSITION != realPosition && realPosition < mData.size){
                mContext.startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(mData[realPosition].linkURL)
                })
            }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(holder is BannerViewHolder && 0 <= position && position < Int.MAX_VALUE){
            val realPosition = position % mData.size
            val bannerData = mData[realPosition]
            if(bannerData.thumbnailURL.isNotBlank()) GlideApp.with(mContext).load(bannerData.thumbnailURL).into(holder.ivBanner)
            holder.tvBannerKeyword.text = bannerData.keyword
            holder.tvBannerTitle.text = bannerData.title
            holder.tvBannerDescription.text = bannerData.description
            val bannerCnt = "${realPosition+1} / ${mData.size}"
            holder.tvBannerCount.text = bannerCnt
        }
    }
}

class BannerViewHolder(parent: ViewGroup, widthDp:Int) : ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_banner_holder, parent, false)){
    val ivBanner:ImageView = itemView.findViewById(R.id.ivBanner)
    val tvBannerKeyword:TextView = itemView.findViewById(R.id.tvBannerKeyword)
    val tvBannerTitle:TextView = itemView.findViewById(R.id.tvBannerTitle)
    val tvBannerDescription:TextView = itemView.findViewById(R.id.tvBannerDescription)
    val tvBannerCount:TextView = itemView.findViewById(R.id.tvBannerCount)

    init {
        itemView.layoutParams = itemView.layoutParams.apply {
            width = widthDp
            height = widthDp
        }
        tvBannerTitle.layoutParams = tvBannerTitle.layoutParams.apply {
            width = (widthDp/3)*2
        }
    }
}