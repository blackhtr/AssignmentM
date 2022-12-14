package com.blackhtr.musinsaassignment.layout

import android.content.Context
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackhtr.musinsaassignment.Glide.GlideApp
import com.blackhtr.musinsaassignment.Utils
import com.blackhtr.musinsaassignment.data.ContentsDTO
import com.blackhtr.musinsaassignment.data.DataDTO
import com.blackhtr.musinsaassignment.data.FooterDTO
import com.blackhtr.musinsaassignment.data.HeaderDTO

object MainHolderManager {
    const val TYPE_GRID     = "GRID"
    const val TYPE_SCROLL   = "SCROLL"
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
                title.let { holder.tvHeaderTitle.text = it.replace(" ","\u00A0") }
                this.iconURL.let {
                    holder.ivHeaderIcon.visibility = if(it.isBlank()) View.GONE else View.VISIBLE
                    if(it.isNotBlank()) GlideApp.with(context).load(it).into(holder.ivHeaderIcon)
                }
                this.linkURL.let {
                    holder.tvHeaderLink.visibility = if(it.isBlank()) View.GONE else View.VISIBLE
                }
            }
        }
    }

    private fun setFooter(context: Context, holder: MainViewHolder, footerData: FooterDTO?){
        footerData.run {
            if(null == this){ holder.inFooter.visibility = View.GONE }
            else{
                holder.inFooter.visibility =  View.VISIBLE
                holder.tvFooterTitle.text = title
                this.iconURL.let {
                    holder.ivFooterIcon.visibility = if(it.isBlank()) View.GONE else View.VISIBLE
                    if(it.isNotBlank()) GlideApp.with(context).load(it).into(holder.ivFooterIcon)
                }

            }
        }
    }

    private fun setContents(context: Context, holder: MainViewHolder, contentsData: ContentsDTO?){
        if(null == contentsData) holder.rvContents.visibility = View.GONE
        else{
            holder.rvContents.visibility = View.VISIBLE
            if(contentsData.type.isNotBlank()){
                holder.rvContents.run {
                    when(contentsData.type){
                        TYPE_GRID -> {
                            layoutManager = GridLayoutManager(context, 3)
                            if(0 == this.itemDecorationCount) this.addItemDecoration(GridDecoration(context, CustomDecoration.DECORATION_SPACE, CustomDecoration.DECORATION_MARGIN))
                            this.layoutParams = this.layoutParams.apply {
                                if(this is MarginLayoutParams){
                                    marginStart = Utils.convertToPixel(context, CustomDecoration.DECORATION_MARGIN)
                                    marginEnd = Utils.convertToPixel(context, CustomDecoration.DECORATION_MARGIN)
                                }
                            }
                            adapter = GoodsAdapter(context, contentsData.type).apply { setData(contentsData.goods) }
                        }
                        TYPE_SCROLL ->{
                            layoutManager = LinearLayoutManager(context).apply { this.orientation = LinearLayoutManager.HORIZONTAL }
                            if(0 == this.itemDecorationCount) this.addItemDecoration(LinearDecoration(context))
                            adapter = GoodsAdapter(context, contentsData.type, holder.rvContents).apply { setData(contentsData.goods) }
                        }
                        TYPE_BANNER -> {
                            layoutManager = LinearLayoutManager(context).apply { this.orientation = LinearLayoutManager.HORIZONTAL }
                            adapter = BannerAdapter(context, contentsData.type, holder.rvContents).apply { setData(contentsData.banners) }
                            scrollToPosition(100 * (adapter as BannerAdapter).getRealItemCount())
                        }
                        TYPE_STYLE -> {
                            layoutManager = GridLayoutManager(context, 2)
                            if(0 == this.itemDecorationCount) this.addItemDecoration(GridDecoration(context, CustomDecoration.DECORATION_SPACE, CustomDecoration.DECORATION_SPACE))
                            this.layoutParams = this.layoutParams.apply {
                                if(this is MarginLayoutParams){
                                    marginStart = Utils.convertToPixel(context, CustomDecoration.DECORATION_MARGIN)
                                    marginEnd = Utils.convertToPixel(context, CustomDecoration.DECORATION_MARGIN)
                                }
                            }
                            adapter = StyleAdapter(context, contentsData.type).apply { setData(contentsData.styles) }
                        }
                    }
                }
            }
        }
    }
}