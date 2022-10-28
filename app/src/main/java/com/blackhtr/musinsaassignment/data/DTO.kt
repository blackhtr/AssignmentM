package com.blackhtr.musinsaassignment.data

import com.google.gson.annotations.SerializedName


data class DataListDTO(
    @SerializedName("data") val dataList: List<DataDTO>
)

data class DataDTO(
    @SerializedName("header") val header:HeaderDTO?,
    @SerializedName("contents") val contents:ContentsDTO?,
    @SerializedName("footer") val footer:FooterDTO?
)

data class HeaderDTO(
    @SerializedName("title") val title:String = "",
    @SerializedName("iconURL") val iconURL:String = "",
    @SerializedName("linkURL") val linkURL:String = "" )

data class ContentsDTO(
    @SerializedName("type") val type:String = "",
    @SerializedName("banners") val banners:List<BannerDTO>?,
    @SerializedName("goods") val goods:List<GoodsDTO>?,
    @SerializedName("styles") val styles:List<StyleDTO>?,
)

data class FooterDTO(
    @SerializedName("type") val type:String = "",
    @SerializedName("title") val title:String = "",
    @SerializedName("iconURL") val iconURL:String = "")

// Contents
data class BannerDTO(
    @SerializedName("linkURL") val linkURL:String = "",
    @SerializedName("thumbnailURL") val thumbnailURL:String = "",
    @SerializedName("title") val title:String = "",
    @SerializedName("description") val description:String = "",
    @SerializedName("keyword") val keyword:String = "",
)
data class GoodsDTO(
    @SerializedName("linkURL") val linkURL:String = "",
    @SerializedName("thumbnailURL") val thumbnailURL:String = "",
    @SerializedName("brandName") val brandName:String = "",
    @SerializedName("price") val price:Int = 0,
    @SerializedName("saleRate") val saleRate:Int = 0,
    @SerializedName("hasCoupon") val hasCoupon:Boolean = false
)
data class StyleDTO(
    @SerializedName("linkURL") val linkURL:String = "",
    @SerializedName("thumbnailURL") val thumbnailURL:String = ""
)






