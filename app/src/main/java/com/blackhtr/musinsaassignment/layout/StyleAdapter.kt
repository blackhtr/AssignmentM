package com.blackhtr.musinsaassignment.layout

import android.annotation.SuppressLint
import android.content.Context
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

class StyleAdapter(context:Context): RecyclerView.Adapter<ViewHolder>() {
    private val mContext = context
    private var mData:MutableList<StyleDTO> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data : List<StyleDTO>?){
        mData.clear()
        data?.run { mData.addAll(this) }
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = mData.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  = StyleViewHolder(parent).apply { setClickListener(this) }
    private fun setClickListener(holder:StyleViewHolder){

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(holder is StyleViewHolder && 0 <= position && position < mData.size){
            val styleData = mData[position]
            if(!styleData.thumbnailURL.isNullOrBlank()) GlideApp.with(mContext).load(styleData.thumbnailURL).into(holder.ivStyle)
        }
    }
}

class StyleViewHolder(parent: ViewGroup) : ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_style_holder, parent, false)){
    val ivStyle:ImageView = itemView.findViewById(R.id.ivStyle)

}