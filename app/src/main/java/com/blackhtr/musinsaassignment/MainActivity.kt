package com.blackhtr.musinsaassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackhtr.musinsaassignment.data.DataListDTO
import com.blackhtr.musinsaassignment.databinding.ActivityMainBinding
import com.blackhtr.musinsaassignment.layout.MainAdapter
import com.blackhtr.musinsaassignment.model.DataViewModel

class MainActivity : AppCompatActivity() {

    lateinit var dataViewModel: DataViewModel
    private var mBinding:ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)
        addObserver()
    }

    private fun addObserver(){
        dataViewModel = ViewModelProvider(this)[DataViewModel::class.java]
        dataViewModel.getDataListDTO.observe(this){
            initLayout(it)
        }
        dataViewModel.requestDataList()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun initLayout(dataList:DataListDTO?){
        mBinding?.rvMain?.run {
            this.layoutManager = LinearLayoutManager(this@MainActivity).apply { this.orientation = LinearLayoutManager.VERTICAL }
            this.adapter = MainAdapter(this@MainActivity).apply { setData(dataList?.dataList) }
        }
    }
}