package com.nadillla.tabunganapp.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.nadillla.tabunganapp.R
import kotlinx.android.synthetic.main.fragment_register2.back
import kotlinx.android.synthetic.main.fragment_result.*

class ResultFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController

//    val manager: FragmentManager = getSupportFragmentManager()

    var get_name : String? = null
    var get_email : String? = null
    var get_password:String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        get_email = arguments?.getString("email")
        get_name = arguments?.getString("name")
        get_password = arguments?.getString("password")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnBackToLogin.setOnClickListener(this)
        back.setOnClickListener(this)

        tvEmail.text=get_email
        tvPassword.text=get_password
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnBackToLogin->navController.navigate(R.id.action_resultFragment_to_mainFragment)


                R.id.back->  activity?.onBackPressed()

        }
    }
//    private fun clearBackStack() {
//        if (manager.backStackEntryCount > 0) {
//            val first =
//                manager.getBackStackEntryAt(0)
//            manager.popBackStack(
//                first.id,
//                FragmentManager.POP_BACK_STACK_INCLUSIVE
//            )
//        }
//    }

}