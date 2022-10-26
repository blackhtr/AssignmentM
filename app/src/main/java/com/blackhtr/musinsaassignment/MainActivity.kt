package com.blackhtr.musinsaassignment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackhtr.musinsaassignment.data.DataListDTO
import com.blackhtr.musinsaassignment.databinding.ActivityMainBinding
import com.blackhtr.musinsaassignment.layout.MainAdapter
import com.blackhtr.musinsaassignment.network.ApiManager
import com.blackhtr.musinsaassignment.network.RetrofitManager
import com.blackhtr.musinsaassignment.network.RetrofitService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {

    //private var mBinding:ActivityMainBinding? = null
    private var mData:DataListDTO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        ApiManager.requestListData(object : ApiManager.ResponseCallBack{
            override fun getResponse(data: DataListDTO?) {
                mData = data
                initLayout()
            }
        })
    }

    override fun onDestroy() {
        //mBinding = null
        super.onDestroy()
    }

    private fun initLayout(){
        rvMain.run {
            this.layoutManager = LinearLayoutManager(this@MainActivity).apply { this.orientation = LinearLayoutManager.VERTICAL }
            this.adapter = MainAdapter(this@MainActivity).apply { setData(mData?.dataList) }
        }
/*        mBinding?.rvMain?.run {
            this.layoutManager = LinearLayoutManager(this@MainActivity).apply {
                this.orientation = LinearLayoutManager.VERTICAL
            }
            this.adapter = MainAdapter().apply { setData(mData?.dataList) }
        }*/
    }
}