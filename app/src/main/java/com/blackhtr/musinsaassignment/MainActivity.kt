package com.blackhtr.musinsaassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackhtr.musinsaassignment.data.DataListDTO
import com.blackhtr.musinsaassignment.databinding.ActivityMainBinding
import com.blackhtr.musinsaassignment.layout.MainAdapter
import com.blackhtr.musinsaassignment.network.ApiManager

class MainActivity : AppCompatActivity() {

    private var mBinding:ActivityMainBinding? = null
    private var mData:DataListDTO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)
        ApiManager.requestListData(object : ApiManager.ResponseCallBack{
            override fun getResponse(data: DataListDTO?) {
                mData = data
                initLayout()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    private fun initLayout(){
        mBinding?.rvMain?.run {
            this.layoutManager = LinearLayoutManager(this@MainActivity).apply { this.orientation = LinearLayoutManager.VERTICAL }
            this.adapter = MainAdapter(this@MainActivity).apply { setData(mData?.dataList) }
        }
    }
}