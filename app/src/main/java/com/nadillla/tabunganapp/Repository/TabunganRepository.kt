package com.nadillla.tabunganapp.Repository

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.nadillla.tabunganapp.Local.Tabungan
import com.nadillla.tabunganapp.Local.TabunganDb
import com.nadillla.tabunganapp.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialog_add_tabungan.view.*

class TabunganRepository(val context:Context) {

    private var tabunganDb= TabunganDb.getInstance(context)

    fun showTabungan(responseHandler: (List<Tabungan>) -> Unit, errorHandler: (Throwable) -> Unit) {
        Observable.fromCallable { tabunganDb.tabunganDao().getDataTabungan() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ item ->
                responseHandler(item)

            }, {
                errorHandler(it)
            })

    }

    fun addTabungan(id:Int?,
                    tgl:String,
                    jum:Int,
                    ket:String,
    responseHandler: (item:Tabungan)->Unit,
        errorHandler: (Throwable) -> Unit)
     {
        Observable.fromCallable {
            tabunganDb.tabunganDao().insertTabungan(Tabungan(id,tgl,jum,ket)) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(Tabungan(id,tgl,jum,ket))
                Toast.makeText(context,"Data berhasil ditambahkan",Toast.LENGTH_SHORT).show()


            },{
                errorHandler(it)
            })
    }

    fun  updateTabungan(id: Int?,tgl: String,jum: Int,ket: String, responseHandler: (item: Tabungan) -> Unit,
    errorHandler: (Throwable) -> Unit){
        Observable.fromCallable {
            tabunganDb.tabunganDao().updateTabungan(Tabungan(id,tgl,jum,ket))
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(Tabungan(id,tgl,jum,ket))
                Toast.makeText(context,"Data berhasil diubah",Toast.LENGTH_SHORT).show()

            },{
                errorHandler(it)
            })
    }

    fun deleteTabungan(item :Tabungan,responseHandler: (item: Tabungan) -> Unit,errorHandler: (Throwable) -> Unit){
        Observable.fromCallable {
            tabunganDb.tabunganDao().deleteTabungan(item)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(item)
                Toast.makeText(context,"Data berhasil dihapus",Toast.LENGTH_SHORT).show()

            }, {
                errorHandler(it)
            })
    }


}

//
//
//    fun insert(tabungan: Tabungan){
//        dao.insertTabungan(tabungan)
//    }
//
//     fun update(tabungan: Tabungan){
//        dao.updateTabungan(tabungan)
//
//    }
//    fun delete(tabungan: Tabungan){
//        dao.deleteTabungan(tabungan)
//    }

