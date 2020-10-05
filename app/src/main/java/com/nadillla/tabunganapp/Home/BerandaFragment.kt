package com.nadillla.tabunganapp.Home

import android.app.Application
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.nadillla.tabunganapp.Helper.SessionManager
import com.nadillla.tabunganapp.Local.TabunganDb
import com.nadillla.tabunganapp.Local.User
import com.nadillla.tabunganapp.R
import com.nadillla.tabunganapp.View.MainActivity
import com.nadillla.tabunganapp.ViewModel.UserViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home2.*
import kotlinx.android.synthetic.main.fragment_beranda.*


class BerandaFragment : Fragment() {

    private var tabunganDb: TabunganDb? = null
    private lateinit var userViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tabunganDb = context?.let { TabunganDb.getInstance(it) }
        userViewModel=ViewModelProvider(this).get(UserViewModel::class.java)

//        get_password = arguments?.getString("password")
//        get_email = arguments?.getString("email")
//        get_name = arguments?.getString("name")

//        userViewModel.loginUser(get_email.toString(),get_password.toString())

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        showTotal()
     //   attachObserve()

    }


//    private fun attachObserve() {
//        userViewModel._responseActionUser.observe(viewLifecycleOwner, Observer { showUser(it) })
//        userViewModel._isErrorUser.observe(viewLifecycleOwner, Observer { errorShowUser(it) })
//    }
//
//    private fun errorShowUser(it:Throwable) {
//        Log.d("TAG", "errorShowUser: ${it.message}")
//        Toast.makeText(context,"${it.message}",Toast.LENGTH_SHORT).show()
//    }
//
//    private fun showUser(it:User) {
//        tvWelcome.text=it.user_name
//
//        Observable.fromCallable { tabunganDb?.userDao()?.getUser(get_email.toString()) }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                tvWelcome.setText("Halo " + it?.user_name).toString()
//            },{
//                Log.d(TAG, "showUser: ${it.message}")
//            })
//    }
//


    private fun showTotal() {
        Observable.fromCallable { tabunganDb?.tabunganDao()?.getTotal() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                tvJml.setText("Rp.$it,-")
                Log.d(TAG, "$it")
            }, {
                Log.d(TAG, "onCreate: gagal,${it.message}")
            })


    }


}