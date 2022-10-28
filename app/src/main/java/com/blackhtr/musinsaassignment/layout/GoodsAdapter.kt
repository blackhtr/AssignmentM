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
import com.blackhtr.musinsaassignment.Glide.GlideApp
import com.blackhtr.musinsaassignment.R
import com.blackhtr.musinsaassignment.data.GoodsDTO
import kotlinx.android.synthetic.main.item_footer.view.*
import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.android.synthetic.main.item_main_holder.view.*
import java.text.DecimalFormat

class GoodsAdapter(context:Context, type:String): BaseAdapter(context, type) {
    constructor(context:Context, type:String, recyclerView: RecyclerView) : this(context, type){ mRecyclerView = recyclerView }

    private val mContext = context
    private var mData:MutableList<GoodsDTO> = mutableListOf()
    private var mRecyclerView:RecyclerView? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun refreshData() {
        if(0 < mData.size){
            mRecyclerView?.scrollToPosition(0)
            mData.shuffle()
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data : List<GoodsDTO>?){
        mData.clear()
        data?.run { mData.addAll(this) }
        totalCount = mData.size
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
            if(goodsData.thumbnailURL.isNotBlank()) GlideApp.with(mContext).load(goodsData.thumbnailURL).into(holder.ivGoodsImg)
            holder.tvGoodsName.text = goodsData.brandName
            holder.tvGoodsPrice.text = priceToString(goodsData.price)
            holder.tvGoodsSale.visibility = if(0 < goodsData.saleRate){
                holder.tvGoodsSale.text = saleRateToString(goodsData.saleRate)
                View.VISIBLE
            } else View.GONE
            holder.tvGoodsCoupon.visibility = if(goodsData.hasCoupon) View.VISIBLE else View.GONE
        }
    }

    private fun priceToString(price:Int):String {
        val decimalFormat = DecimalFormat("###,###,###원")
        return decimalFormat.format(price)
    }

    private fun saleRateToString(saleRate:Int):String = if(0 < saleRate) "${saleRate}%" else ""


}





class GoodsViewHolder(parent: ViewGroup, widthDp:Int) : ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_goods_holder, parent, false)){
    val ivGoodsImg:ImageView = itemView.findViewById(R.id.ivGoodsImg)
    val tvGoodsName:TextView = itemView.findViewById(R.id.tvGoodsName)
    val tvGoodsPrice:TextView = itemView.findViewById(R.id.tvGoodsPrice)
    val tvGoodsSale:TextView = itemView.findViewById(R.id.tvGoodsSale)
    val tvGoodsCoupon:TextView = itemView.findViewById(R.id.tvGoodsCoupon)
    init {
        itemView.layoutParams = itemView.layoutParams.apply {
            width = widthDp
            height = 3*(widthDp/2)
        }
    }
}