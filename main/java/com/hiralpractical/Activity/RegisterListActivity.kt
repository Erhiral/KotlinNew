package com.hiralpractical.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.hiralpractical.Adapter.RegisterListAdapter
import com.hiralpractical.Database.Table.RegisterTable
import com.hiralpractical.R
import com.hiralpractical.ViewModel.RegisterViewmodel
import kotlinx.android.synthetic.main.activity_register_list.*

class RegisterListActivity: AppCompatActivity(),RegisterItemListener {
    private val registerViewmodel: RegisterViewmodel by lazy {
        ViewModelProvider(this).get(RegisterViewmodel::class.java)
    }

    private val registerListAdapter: RegisterListAdapter by lazy {
        RegisterListAdapter( this,this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_list)
        add_register.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            finish()
        }

        val livedata = registerViewmodel.getAllUserRegisterDetails()
        livedata.observe(this, object : Observer<List<RegisterTable>> {
            override fun onChanged(t: List<RegisterTable>?) {
                if (t!!.isNotEmpty()) {
                    registerListAdapter.addall(t as ArrayList<RegisterTable>)
                } else {
                    Toast.makeText(
                        this@RegisterListActivity,
                        "No Data Found",
                                Toast.LENGTH_LONG
                    ).show()


                }
            }
        })

        val layoutManager = LinearLayoutManager(this)
        rv_register_list.layoutManager = layoutManager
        rv_register_list.itemAnimator = DefaultItemAnimator()
        rv_register_list.adapter = registerListAdapter
     }

    override fun ItemClicked(registerTable: RegisterTable,name:String) {

        if(name =="Edit"|| name.contains("Edit")) {
            Log.e("mytag"," registerTable.id"+ registerTable.id)
            registerViewmodel.updateUser(
                registerTable.id,
                registerTable.name,
                registerTable.email,
                registerTable.phone
            )
        }else if(name=="delete"|| name.contains("delete")){
            registerViewmodel.deleteUser(registerTable.id)

        }
    }


}