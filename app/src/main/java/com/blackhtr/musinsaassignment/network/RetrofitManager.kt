package com.blackhtr.musinsaassignment.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager {
    companion object{
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://meta.musinsa.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}