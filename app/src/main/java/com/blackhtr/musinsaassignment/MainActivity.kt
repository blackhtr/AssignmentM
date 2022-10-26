package com.blackhtr.musinsaassignment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.blackhtr.musinsaassignment.data.DataListDTO
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

        service.getListData().enqueue(object :Callback<DataListDTO>{
            override fun onResponse(call: Call<DataListDTO>, response: Response<DataListDTO>) {
                response.body()?.dataList?.get(0)?.contents?.type?.run {
                    Log.i("TRHEO", "onResponse $this")
                }
            }
            override fun onFailure(call: Call<DataListDTO>, t: Throwable) {
                Log.i("TRHEO", "onFailure $t")
            }

        })



    }
}