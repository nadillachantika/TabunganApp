package com.nadillla.tabunganapp.View

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.nadillla.tabunganapp.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        val intent = Intent(applicationContext, MainActivity::class.java)
//        val fm = supportFragmentManager
//        val f = fm.findFragmentById(R.id.resultFragment)
//
//        if (fm.backStackEntryCount > 0) {
//            fm.popBackStack()
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//
//        } else {
//        }
//    }

    override fun onBackPressed() {
        super.onBackPressed()
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }




}