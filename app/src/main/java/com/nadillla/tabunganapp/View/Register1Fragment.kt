package com.nadillla.tabunganapp.View

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.nadillla.tabunganapp.Helper.SessionManager
import com.nadillla.tabunganapp.R
import com.nadillla.tabunganapp.ViewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_register1.*

class Register1Fragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register1, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnNext.setOnClickListener(this)
        btnBack.setOnClickListener(this)

        attachObserve()
    }

    private fun attachObserve() {
        userViewModel._responseActionUser.observe(viewLifecycleOwner, Observer { gotUser() })
        userViewModel._isErrorUser.observe(viewLifecycleOwner, Observer { emptyUser(it) })

    }

    private fun emptyUser(it: Throwable?) {
        Log.d("TAG", "Lanjut register, email belum ada")

        val bundle = bundleOf(
            "name" to edName.text.toString(),
            "email" to edEmail.text.toString()
        )
        navController.navigate(R.id.action_register1Fragment_to_register2Fragment, bundle)
    }

    private fun gotUser() {

        Log.d("TAG", "email sudah ada")
        Toast.makeText(context, "Email sudah terdaftar", Toast.LENGTH_SHORT).show()


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnNext ->
                if (edName.text.toString().isEmpty()) {
                    edName.error = "Nama tidak boleh kosong"
                } else if (edEmail.text.toString().isEmpty()) {
                    edEmail.error = "Email tidak boleh kosong"

                } else {
                    validasiEmail()
                    if (validasiEmail() == true) {
                        userViewModel.gotEmail(edEmail.text.toString())
                    }

                }

            R.id.btnBack -> activity?.onBackPressed()
        }
    }

    private fun validasiEmail(): Boolean {
        var Email: String = edEmail.text.toString()
        if (Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            return true
        } else {
            edEmail.error = "Masukkan alamat email"
            return false
        }
    }
//    fun clearStack()
//    {
//        val backStackEntry = supportFragmentManager.backStackEntryCount
//        if (backStackEntry > 0) {
//            for (i in 0 until backStackEntry) {
//                supportFragmentManager.popBackStackImmediate()
//            }
//        }
//        if (supportFragmentManager.fragments.size > 0) {
//            supportFragmentManager.fragments.forEach {
//                if (it != null) {
//                    supportFragmentManager.beginTransaction().remove(it).commit()
//                }
//            }
//        }
//    }



}