package com.hiralpractical.Activity

import com.hiralpractical.Database.Table.RegisterTable


interface RegisterItemListener {
    fun ItemClicked(registerTable: RegisterTable,name:String)
}