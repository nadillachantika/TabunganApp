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
import androidx.navigation.fragment.findNavController
import com.nadillla.tabunganapp.Local.User
import com.nadillla.tabunganapp.R
import com.nadillla.tabunganapp.ViewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment  : Fragment(), View.OnClickListener {

    lateinit var navController: NavController
    private lateinit var userViewModel: UserViewModel


    var get_email: String? = null
    var get_password: String? = null
    var get_name:String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnRegistermain.setOnClickListener(this)
        btnLogin.setOnClickListener(this)


        attachObserve()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        get_email = arguments?.getString("email")
        get_password = arguments?.getString("password")
        get_name = arguments?.getString("name")
    }



    private fun attachObserve() {
        userViewModel._responseActionUser.observe(viewLifecycleOwner, Observer { loginSuccess(it) })
        userViewModel._isErrorUser.observe(viewLifecycleOwner, Observer { errorLogin(it) })
    }

    private fun errorLogin(it:Throwable) {
        Toast.makeText(context,"Email belum terdaftar",Toast.LENGTH_SHORT).show()
        Log.d("TAG", "errorLogin: ${it.message}")
    }

    private fun loginSuccess(it:User) {
        Log.d("TAG", "loginSuccess: OK")
        val bundle= bundleOf(
            "password" to edPassword.text.toString(),
            "email" to edEmail.text.toString()
        )
        navController.navigate(R.id.action_mainFragment_to_home2Activity,bundle)
//        navController.navigate(R.id.action_mainFragment_to_berandaFragment,bundle)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnRegistermain->navController.navigate(R.id.action_mainFragment_to_register1Fragment)

            R.id.btnLogin->

                if(edEmail.text.toString().isEmpty()){
                    edEmail.error = "Email tidak boleh kosong"
                }else if(edPassword.text.toString().isEmpty()){
                    edPassword.error="Password tidak boleh kosong"
                }else{
                    userViewModel.loginUser(edEmail.text.toString(),edPassword.text.toString())
                }

        }
    }



}