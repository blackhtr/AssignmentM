package com.blackhtr.musinsaassignment.network

import com.blackhtr.musinsaassignment.data.DataListDTO
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("interview/list.json")
    fun getListData() : Call<DataListDTO>
}