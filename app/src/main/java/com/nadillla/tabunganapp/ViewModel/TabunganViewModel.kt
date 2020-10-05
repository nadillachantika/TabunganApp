package com.nadillla.tabunganapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nadillla.tabunganapp.Local.Tabungan
import com.nadillla.tabunganapp.Repository.TabunganRepository


class TabunganViewModel (application :Application): AndroidViewModel(application){

    val repository= TabunganRepository(application.applicationContext)
    var responseData = MutableLiveData<List<Tabungan>>()
    var _responseData : LiveData<List<Tabungan>> =responseData

    var responseAction=MutableLiveData<Tabungan>()
    var _responseAction : LiveData<Tabungan> = responseAction

    var isError = MutableLiveData<Throwable>()
    var _isError : LiveData<Throwable> = isError

    fun getListTabungan(){
        repository.showTabungan({
            responseData.value=it

        },{
            isError.value=it

        })
    }

    fun addTabungan(id:Int?,tgl:String, jumlah:Int, keterangan:String){
            repository.addTabungan(id,tgl,jumlah,keterangan,{
                responseAction.value=it

            },{
                isError.value=it
            })
        }

    fun updateTabungan(id: Int?,tgl: String,jumlah: Int,keterangan: String){
        repository.updateTabungan(id,tgl,jumlah,keterangan,{
            responseAction.value=it
        },{
            isError.value=it
        })
    }

    fun deleteTabungan(item:Tabungan){
        repository.deleteTabungan(item,{
            responseAction.value=it
        },{
            isError.value=it
        })
    }

    }





