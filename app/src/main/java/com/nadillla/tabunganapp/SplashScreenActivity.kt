package com.nadillla.tabunganapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.nadillla.tabunganapp.Helper.SessionManager
import com.nadillla.tabunganapp.Home.Home2Activity
import com.nadillla.tabunganapp.View.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val session = SessionManager(this)

        Handler().postDelayed(Runnable {

            if(session.login?:true){
                startActivity(Intent(this,Home2Activity::class.java))

            }else{
                startActivity(Intent(this,MainActivity::class.java))
            }

            finish()
        },2000)
    }
}