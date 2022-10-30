package com.blackhtr.musinsaassignment.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blackhtr.musinsaassignment.data.DataListDTO
import com.blackhtr.musinsaassignment.network.ApiManager

class DataViewModel(app:Application):AndroidViewModel(app) {
    private val mutableLiveDataListDTO = MutableLiveData<DataListDTO?>()
    val getDataListDTO :LiveData<DataListDTO?> get() = mutableLiveDataListDTO
    fun setDataListDTO(dataListDTO : DataListDTO?) = mutableLiveDataListDTO.postValue(dataListDTO)

    fun requestDataList(){
        ApiManager.requestListData(object : ApiManager.ResponseCallBack{
            override fun getResponse(data: DataListDTO?) {
                setDataListDTO(data)
            }
        })
    }
}