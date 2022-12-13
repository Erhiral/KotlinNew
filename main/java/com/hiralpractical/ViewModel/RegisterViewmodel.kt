package com.hiralpractical.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.hiralpractical.Database.Repository.RegisterRepository
import com.hiralpractical.Database.Table.RegisterTable


class RegisterViewmodel(application: Application): AndroidViewModel(application) {

    private var registerRepository: RegisterRepository = RegisterRepository(getApplication())
    private var registerdata : LiveData<List<RegisterTable>>? = null

    fun insert(registerTable: RegisterTable)
    {
        registerRepository.insert(registerTable,getApplication())
    }

    fun getAllUserRegisterDetails() : LiveData<List<RegisterTable>>
    {
        registerdata = registerRepository.getAllUserRegisterDetails()
        return registerdata as LiveData<List<RegisterTable>>
    }

    fun deleteAll() {
        registerRepository.deleteAll()
    }

    fun deleteUser(id: Long) {
        registerRepository.deleteUser(id)

    }

    fun updateUser(id:Long,name: String?,Email: String?,phone: String? ) {
        registerRepository.updateUser(id,name,Email,phone)

    }

}