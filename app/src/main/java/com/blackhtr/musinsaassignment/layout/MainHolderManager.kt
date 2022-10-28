package com.blackhtr.musinsaassignment.layout

import android.content.Context
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackhtr.musinsaassignment.Glide.GlideApp
import com.blackhtr.musinsaassignment.data.ContentsDTO
import com.blackhtr.musinsaassignment.data.DataDTO
import com.blackhtr.musinsaassignment.data.FooterDTO
import com.blackhtr.musinsaassignment.data.HeaderDTO

object MainHolderManager {
    const val TYPE_GRID     = "GRID"
    private const val TYPE_SCROLL   = "SCROLL"
    private const val TYPE_BANNER   = "BANNER"
    const val TYPE_STYLE    = "STYLE"

    const val TYPE_FOOTER_MORE     = "MORE"
    const val TYPE_FOOTER_REFRESH  = "REFRESH"

    fun setHolderData(context: Context, holder: MainViewHolder, data: DataDTO){
        setHeader(context, holder, data.header)
        setContents(context, holder, data.contents)
        setFooter(context, holder, data.footer)
    }

    private fun setHeader(context: Context, holder: MainViewHolder, headerData: HeaderDTO?){
        headerData.run {
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
    }

    private fun setFooter(context: Context, holder: MainViewHolder, footerData: FooterDTO?){
        footerData.run {
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

    private fun setContents(context: Context, holder: MainViewHolder, contentsData: ContentsDTO?){
        if(null == contentsData) holder.rvContents.visibility = View.GONE
        else{
            holder.rvContents.visibility = View.VISIBLE
            if(!contentsData.type.isNullOrBlank()){
                holder.rvContents.run {
                    when(contentsData.type){
                        TYPE_GRID -> {
                            layoutManager = GridLayoutManager(context, 3)
                            adapter = GoodsAdapter(context, contentsData.type).apply { setData(contentsData.goods) }
                        }
                        TYPE_SCROLL ->{
                            layoutManager = LinearLayoutManager(context).apply { this.orientation = LinearLayoutManager.HORIZONTAL }
                            adapter = GoodsAdapter(context, contentsData.type).apply { setData(contentsData.goods) }
                        }
                        TYPE_BANNER -> {
                            layoutManager = LinearLayoutManager(context).apply { this.orientation = LinearLayoutManager.HORIZONTAL }
                            adapter = BannerAdapter(context, holder.rvContents, contentsData.type).apply { setData(contentsData.banners) }
                        }
                        TYPE_STYLE -> {
                            layoutManager = GridLayoutManager(context, 2)
                            adapter = StyleAdapter(context, contentsData.type).apply { setData(contentsData.styles) }
                        }
                    }
                }
            }
        }
    }
}