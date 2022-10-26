package com.blackhtr.musinsaassignment.network

import android.util.Log
import com.blackhtr.musinsaassignment.data.DataListDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

object ApiManager {
    interface ResponseCallBack{
        fun getResponse(data : DataListDTO?)
    }
    fun requestListData(cb : ResponseCallBack){
        val service = RetrofitManager.retrofit.create<RetrofitService>()
        service.getListData().enqueue(object : Callback<DataListDTO> {
            override fun onResponse(call: Call<DataListDTO>, response: Response<DataListDTO>) { cb.getResponse(if(response.isSuccessful) response.body() else null) }
            override fun onFailure(call: Call<DataListDTO>, t: Throwable) { cb.getResponse(null) }
        })
    }

}