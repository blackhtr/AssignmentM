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
import com.blackhtr.musinsaassignment.data.DataDTO
import com.blackhtr.musinsaassignment.data.GoodsDTO
import kotlinx.android.synthetic.main.item_footer.view.*
import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.android.synthetic.main.item_main_holder.view.*

class GoodsAdapter(context:Context, type:String): RecyclerView.Adapter<ViewHolder>() {
    private val mContext = context
    private val mType = type
    private var mData:MutableList<GoodsDTO> = mutableListOf()
    private var showLine:Int = 2

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data : List<GoodsDTO>?){
        mData.clear()
        data?.run { mData.addAll(this) }
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = when(mType){
        MainHolderManager.TYPE_GRID -> if(showLine*3 < mData.size) showLine*3 else mData.size
        else -> mData.size
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  = GoodsViewHolder(parent).apply { setClickListener(this) }
    private fun setClickListener(holder:GoodsViewHolder){

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(holder is GoodsViewHolder && 0 <= position && position < mData.size){
            val goodsData = mData[position]
            if(!goodsData.thumbnailURL.isNullOrBlank()) GlideApp.with(mContext).load(goodsData.thumbnailURL).into(holder.ivGoods)
        }
    }
}

class GoodsViewHolder(parent: ViewGroup) : ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_goods_holder, parent, false)){
    val ivGoods:ImageView = itemView.findViewById(R.id.ivGoods)
}