package com.hiralpractical.Database.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.hiralpractical.Database.AppDatabase
import com.hiralpractical.Database.Table.RegisterTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterRepository (context: Context)  {

    init {
            appDatabase = initializeDB(context)
        }

    companion object {
            var appDatabase: AppDatabase? = null
            var registerTable: LiveData<List<RegisterTable>>? = null

            fun initializeDB(context: Context): AppDatabase {
                return AppDatabase.getInstance(context)
            }
        }

        fun insert(registerTable: RegisterTable, context: Context) {
            CoroutineScope(Dispatchers.IO).launch {
                val id = appDatabase!!.registerDao().insert(registerTable)

            }
        }

        fun getAllUserRegisterDetails(): LiveData<List<RegisterTable>> {
            registerTable = appDatabase!!.registerDao().getAllUserRegisterDetails()
            return registerTable as LiveData<List<RegisterTable>>
        }

        fun deleteAll() {
            CoroutineScope(Dispatchers.IO).launch {
                appDatabase!!.registerDao().deleteAllData()
            }
        }

        fun deleteUser(id: Long) {
            CoroutineScope(Dispatchers.IO).launch {
                appDatabase!!.registerDao().getdeleteUser(id)
            }

        }

        fun updateUser(id:Long,name: String?,Email: String?,phone: String? ) {
            CoroutineScope(Dispatchers.IO).launch {
                appDatabase!!.registerDao().updateUser(id, name, Email, phone)
            }

        }

  }
