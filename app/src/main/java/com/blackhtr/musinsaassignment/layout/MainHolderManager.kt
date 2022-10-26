package com.blackhtr.musinsaassignment.layout

import android.content.Context
import android.view.View
import com.blackhtr.musinsaassignment.Glide.GlideApp
import com.blackhtr.musinsaassignment.data.DataDTO

object MainHolderManager {

    fun setHolderData(context: Context, holder: MainViewHolder, data: DataDTO){
        data.header.run {
            if(null == this){
                holder.inHeader.visibility = View.GONE
            }else{
                holder.inHeader.visibility = View.VISIBLE
                title?.let { holder.tvHeaderTitle.text = it.replace(" ","\u00A0") }
                this.iconURL.let {
                    holder.ivHeaderIcon.visibility = if(it.isNullOrBlank()) View.GONE else View.VISIBLE
                    if(!it.isNullOrBlank()) GlideApp.with(context).load(it).into(holder.ivHeaderIcon)
                }
                this.linkURL.let {
                    holder.tvHeaderLink.visibility = if(it.isNullOrBlank()) View.GONE else View.VISIBLE
                }
            }
        }


        data.footer.run {
            if(null == this){ holder.inFooter.visibility = View.GONE }
            else{
                holder.inFooter.visibility =  View.VISIBLE
                holder.tvFooterTitle.text = title?: ""
                this.iconURL.let {
                    holder.ivFooterIcon.visibility = if(it.isNullOrBlank()) View.GONE else View.VISIBLE
                    if(!it.isNullOrBlank()) GlideApp.with(context).load(it).into(holder.ivFooterIcon)
                }

            }

        }

    }

}