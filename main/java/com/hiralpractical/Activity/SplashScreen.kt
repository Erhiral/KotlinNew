package com.hiralpractical.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hiralpractical.Database.Table.RegisterTable
import com.hiralpractical.Other.SharedPreferences
import com.hiralpractical.R
import com.hiralpractical.ViewModel.RegisterViewmodel

class SplashScreen : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    private val registerViewmodel: RegisterViewmodel by lazy {
        ViewModelProvider(this).get(RegisterViewmodel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val livedata = registerViewmodel.getAllUserRegisterDetails()
        livedata.observe(this, object : Observer<List<RegisterTable>> {
            override fun onChanged(t: List<RegisterTable>?) {
                if (t!!.isEmpty()) {
                    sharedPreferences.usernameid("userId", "0")
                }
            }
        })

        sharedPreferences = SharedPreferences(this)

        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
            val id=sharedPreferences.getusernameid("userId")
            if(id==null){
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }else{
              if(id=="0"){
                  val intent = Intent(this, RegisterActivity::class.java)
                  startActivity(intent)
                  finish()
              } else{
                  val intent = Intent(this, RegisterListActivity::class.java)
                  startActivity(intent)
                  finish()
              }
            }

        }, 3000) // 3000 is the delayed time in milliseconds.
    }

}