package com.blackhtr.musinsaassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.blackhtr.musinsaassignment.data.DataDTO
import com.blackhtr.musinsaassignment.network.RetrofitManager
import com.blackhtr.musinsaassignment.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = RetrofitManager.retrofit.create<RetrofitService>()

        service.getListData().enqueue(object :Callback<DataDTO>{
            override fun onResponse(call: Call<DataDTO>, response: Response<DataDTO>) {
                Log.i("TRHEO", "onResponse $response")
            }

            override fun onFailure(call: Call<DataDTO>, t: Throwable) {
                Log.i("TRHEO", "onFailure $t")
            }

        })



    }
}