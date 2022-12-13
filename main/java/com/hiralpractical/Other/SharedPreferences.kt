package com.hiralpractical.Other

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(val context: Context) {

    private val PREFS_NAME = "sharedpreferences"

    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)


    fun usernameid(KEY_NAME: String, username: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(KEY_NAME, username)
        editor.apply()
    }

    fun getusernameid(KEY_NAME: String): String? {
        return sharedPreferences.getString(KEY_NAME, null)
    }

}