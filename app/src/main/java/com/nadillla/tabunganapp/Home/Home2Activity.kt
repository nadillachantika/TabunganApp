package com.nadillla.tabunganapp.Home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.nadillla.tabunganapp.Helper.SessionManager
import com.nadillla.tabunganapp.R
import com.nadillla.tabunganapp.View.MainActivity
import kotlinx.android.synthetic.main.activity_home2.*


class Home2Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)
        val session = SessionManager(this)
        setTitle(session.nama)



        val navController  = Navigation.findNavController(this,R.id.home_nav_host_fragment)
        NavigationUI.setupWithNavController(bottomNavigation, navController)





    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.login_options,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        if(id==R.id.logout){
            val session = SessionManager(this)
            AlertDialog.Builder(this).apply {
               setTitle("Keluar")
               setMessage("Yakin ingin keluar ?")
               setCancelable(false)

               setPositiveButton("Yakin") { dialogInterface, i ->
                   session.logout()
                   val intent = Intent(applicationContext, MainActivity::class.java)
                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                   intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                   intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                   startActivity(intent)
               }
               setNegativeButton("Batal") { dialogInterface, i ->
                   dialogInterface.dismiss()
               }
           }.show()

        }
        return super.onOptionsItemSelected(item)
    }




}