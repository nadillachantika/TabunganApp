package com.nadillla.tabunganapp.Helper

import android.content.Context
import android.content.SharedPreferences
import com.nadillla.tabunganapp.ViewModel.UserViewModel

class SessionManager(var context: Context) {

    var pref : SharedPreferences?= null
    var editor : SharedPreferences.Editor?=null
    var PREF_NAME = "LOGINMAGISTER"

    var ISLOGIN="isLogin"
    var NAMA = "name"
    var EMAIL = "email"

    init{
        pref = context.getSharedPreferences(PREF_NAME,0)
        editor = pref?.edit()


    }

    var login: Boolean?
    get() = pref?.getBoolean(ISLOGIN,false)
    set(login){
        editor?.putBoolean(ISLOGIN,true)
        editor?.commit()
    }

    var nama :String?
    get() = pref?.getString(NAMA,"")
    set(nama){
        editor?.putString(NAMA,nama)
        editor?.commit()
    }

    fun logout(){
        editor?.clear()
        editor?.commit()
    }
}