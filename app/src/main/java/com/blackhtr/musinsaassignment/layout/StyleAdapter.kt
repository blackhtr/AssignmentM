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
import com.blackhtr.musinsaassignment.data.StyleDTO
import kotlinx.android.synthetic.main.item_footer.view.*
import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.android.synthetic.main.item_main_holder.view.*

class StyleAdapter(context:Context, type:String): BaseAdapter(context, type) {
    private val mContext = context
    private var mData:MutableList<StyleDTO> = mutableListOf()

    override fun refreshData() {}

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data : List<StyleDTO>?){
        mData.clear()
        data?.run { mData.addAll(this) }
        totalCount = mData.size
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = if(showLine*lineCount < mData.size) showLine*lineCount else mData.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  = StyleViewHolder(parent, displayWidth/2).apply { setClickListener(this) }
    private fun setClickListener(holder:StyleViewHolder){
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
        if(holder is StyleViewHolder && 0 <= position && position < mData.size){
            val styleData = mData[position]
            if(styleData.thumbnailURL.isNotBlank()) GlideApp.with(mContext).load(styleData.thumbnailURL).into(holder.ivStyle)
        }
    }
}

class StyleViewHolder(parent: ViewGroup, widthDp:Int) : ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_style_holder, parent, false)){
    val ivStyle:ImageView = itemView.findViewById(R.id.ivStyle)
    init {
        itemView.layoutParams = itemView.layoutParams.apply {
            width = widthDp
            height = 3*(widthDp/2)
        }
    }

}