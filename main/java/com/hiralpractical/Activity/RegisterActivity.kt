package com.hiralpractical.Activity

import android.content.Intent

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hiralpractical.Database.Repository.RegisterRepository
import com.hiralpractical.Database.Table.RegisterTable
import com.hiralpractical.Other.SharedPreferences
import com.hiralpractical.R
import com.hiralpractical.ViewModel.RegisterViewmodel
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.view.*

class RegisterActivity  : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    private val registerViewmodel: RegisterViewmodel by lazy {
        ViewModelProvider(this).get(RegisterViewmodel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        sharedPreferences = SharedPreferences(this)

        val livedata = registerViewmodel.getAllUserRegisterDetails()
        livedata.observe(this, object : Observer<List<RegisterTable>> {
            override fun onChanged(t: List<RegisterTable>?) {
                if (t!!.isEmpty()) {
                    sharedPreferences.usernameid("userId", "0")
                }
            }
        })

        btn_register.setOnClickListener {
            if(et_name.text.toString().trim().isNullOrEmpty()){
                Toast.makeText(this@RegisterActivity,"Please Enter Name",Toast.LENGTH_LONG).show()
            }else if(et_email.text.toString().trim().isNullOrEmpty()){
                Toast.makeText(this@RegisterActivity,"Please Enter Email",Toast.LENGTH_LONG).show()
            }else if(et_phone.text.toString().trim().isNullOrEmpty()){
                Toast.makeText(this@RegisterActivity,"Please Enter Phone",Toast.LENGTH_LONG).show()
            }else if(et_phone.text.toString().trim().length!=10){
                Toast.makeText(this@RegisterActivity,"Please Enter Valid Phone",Toast.LENGTH_LONG).show()
            }else{
                val registerTable = RegisterTable()
                registerTable.name= et_name.text.toString().trim()
                registerTable.email= et_email.text.toString().trim()
                registerTable.phone= et_phone.text.toString().trim()
                registerViewmodel.insert(registerTable)
                Toast.makeText(this@RegisterActivity,"Register Successfully",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,RegisterListActivity::class.java))
                sharedPreferences.usernameid("userId", "1")
                finish()

            }

        }


    }
}