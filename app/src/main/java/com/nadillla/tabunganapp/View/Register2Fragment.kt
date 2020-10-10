package com.nadillla.tabunganapp.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.nadillla.tabunganapp.Local.TabunganDb
import com.nadillla.tabunganapp.Local.User
import com.nadillla.tabunganapp.R
import com.nadillla.tabunganapp.ViewModel.UserViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_register1.*
import kotlinx.android.synthetic.main.fragment_register2.*

class Register2Fragment : Fragment(), View.OnClickListener {

    private var tabunganDatabase: TabunganDb? = null

    lateinit var navController: NavController
    private lateinit var userViewModel: UserViewModel

    var get_name: String? = null
    var get_email: String? = null
    var get_password: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register2, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tabunganDatabase = context?.let { TabunganDb.getInstance(it) }

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        get_name = arguments?.getString("name")
        get_email = arguments?.getString("email")
        get_password = arguments?.getString("password")



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        btnFinish.setOnClickListener(this)
        back.setOnClickListener(this)
        tvHalo.text = "Halo $get_name, untuk melanjutkan register silahkan isi password dibawah"
        tvEmail.text = get_email


        attachObserve()

    }

    private fun attachObserve() {
        //register
//        userViewModel._responseActionUser.observe(viewLifecycleOwner, Observer { registUser() })
//        userViewModel._isErrorUser.observe(viewLifecycleOwner, Observer { errorRegister(it) })

        //cek email
        userViewModel._responseActionUser.observe(viewLifecycleOwner, Observer { cekEmail() })
        userViewModel._isErrorUser.observe(viewLifecycleOwner, Observer { errorEmail() })

    }

    private fun errorEmail() {


        userViewModel.registerUser(
            null,
            get_name.toString(),
            get_email.toString(),
            edPassword.text.toString(),
            edConfirmPass.text.toString()
        )

        val bundle = bundleOf(
            "password" to edPassword.text.toString(),
            "email" to tvEmail.text.toString()
        )
        navController.navigate(R.id.action_register2Fragment_to_resultFragment, bundle)
    }

    private fun cekEmail() {
        Toast.makeText(context, "Register gagal, Email sudah terdaftar", Toast.LENGTH_SHORT).show()
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnFinish -> userViewModel.gotEmail(get_email.toString())
            R.id.back -> activity?.onBackPressed()
        }

    }

}


