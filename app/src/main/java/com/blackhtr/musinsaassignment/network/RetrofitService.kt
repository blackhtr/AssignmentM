package com.blackhtr.musinsaassignment.network

import com.blackhtr.musinsaassignment.data.DataDTO
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface RetrofitService {
    @FormUrlEncoded
    @GET("")
    fun getListData() : Call<DataDTO>
}