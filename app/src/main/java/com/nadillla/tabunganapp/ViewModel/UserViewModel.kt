package com.nadillla.tabunganapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nadillla.tabunganapp.Helper.SessionManager
import com.nadillla.tabunganapp.Local.User
import com.nadillla.tabunganapp.Repository.UserRepository

class UserViewModel(application: Application):AndroidViewModel(application) {


    val repoUser = UserRepository(application.applicationContext)
    var responseDataUser = MutableLiveData<List<User>>()
    var _responseDataUser : LiveData<List<User>> =responseDataUser

    var responseActionUser= MutableLiveData<User>()
    var _responseActionUser : LiveData<User> = responseActionUser

    var isErrorUser = MutableLiveData<Throwable>()
    var _isErrorUser : LiveData<Throwable> = isErrorUser







    fun gotEmail(email: String){
        repoUser.cekEmail(email,{
            responseActionUser.value=it

        },{
            isErrorUser.value=it
        })
    }
    fun loginUser(email:String,password:String){
        repoUser.loginUser(email,password,{
            responseActionUser.value=it


        },{

            isErrorUser.value=it

        })


    }


    fun registerUser(id:Int?,nama:String,email:String,password: String,passwordKonf:String){
        repoUser.registerUser(id,nama,email,password,passwordKonf,{
            responseActionUser.value = it
        },{
            isErrorUser.value=it
        })
    }
}