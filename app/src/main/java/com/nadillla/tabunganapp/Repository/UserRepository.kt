package com.nadillla.tabunganapp.Repository

import android.content.Context
import android.text.method.TextKeyListener.clear
import android.widget.Toast
import com.nadillla.tabunganapp.Helper.SessionManager
import com.nadillla.tabunganapp.Local.Tabungan
import com.nadillla.tabunganapp.Local.TabunganDb
import com.nadillla.tabunganapp.Local.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class UserRepository(val context: Context) {

    private var tabunganDb=TabunganDb.getInstance(context)
    val session = SessionManager(context)


    fun registerUser(id:Int?,name:String,email:String,password:String,passwordKonf:String,responseHandler:(User)->Unit,errorHandler:(Throwable)->Unit) {
   
        if (password != passwordKonf) {
            Toast.makeText(context, "Password Tidak Sama", Toast.LENGTH_SHORT).show()
        } else if(password.length<6) {
            Toast.makeText(context, "Password harus lebih dari 5 karakter",Toast.LENGTH_SHORT).show()
        }else{
            Observable.fromCallable { tabunganDb.userDao().register(User(id,name,email, password))}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ item ->
                    responseHandler(User(id,name,email,password))

                }, {
                    errorHandler(it)
                })
        }
    }


    fun loginUser(email:String, password:String, responseHandler: (User) -> Unit, errorHandler: (Throwable) -> Unit){

        Observable.fromCallable { tabunganDb.userDao().login(email,password)}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({item->
                responseHandler(item)
                session.nama=item.user_name
                session.login = true

            },{
                errorHandler(it)
            })
    }

    fun cekEmail(email: String, responseHandler: (User) -> Unit, errorHandler: (Throwable) -> Unit){
        Observable.fromCallable { tabunganDb.userDao().getUser(email) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({item->
                responseHandler(item)

            },{
                errorHandler(it)

            })

    }


}