package com.blackhtr.musinsaassignment.layout

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.blackhtr.musinsaassignment.Glide.GlideApp
import com.blackhtr.musinsaassignment.R
import com.blackhtr.musinsaassignment.data.GoodsDTO
import kotlinx.android.synthetic.main.item_footer.view.*
import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.android.synthetic.main.item_main_holder.view.*

class GoodsAdapter(context:Context, type:String): BaseAdapter(context, type) {
    private val mContext = context
    private var mData:MutableList<GoodsDTO> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    override fun refreshData() {
        mData.shuffle()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data : List<GoodsDTO>?){
        mData.clear()
        data?.run { mData.addAll(this) }
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = when(contentsType){
        MainHolderManager.TYPE_GRID -> if(showLine*lineCount < mData.size) showLine*lineCount else mData.size
        else -> mData.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  = GoodsViewHolder(parent, displayWidth/3).apply { setClickListener(this) }
    private fun setClickListener(holder:GoodsViewHolder){
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            if(RecyclerView.NO_POSITION != position && position < mData.size){
                mContext.startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(mData[position].linkURL)
                })
            }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(holder is GoodsViewHolder && 0 <= position && position < mData.size){
            val goodsData = mData[position]
            if(!goodsData.thumbnailURL.isNullOrBlank()) GlideApp.with(mContext).load(goodsData.thumbnailURL).into(holder.ivGoods)
        }
    }
}

class GoodsViewHolder(parent: ViewGroup, widthDp:Int) : ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_goods_holder, parent, false)){
    val ivGoods:ImageView = itemView.findViewById(R.id.ivGoods)
    init {
        itemView.layoutParams = itemView.layoutParams.apply {
            width = widthDp
            height = 3*(widthDp/2)
        }
    }
}