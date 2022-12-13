package com.hiralpractical

import android.app.Application
import com.hiralpractical.Database.AppDatabase

class App : Application() {



    override fun onCreate() {
        super.onCreate()
        AppDatabase.getInstance(this); //--AppDatabase_Impl does not exist

    }


}