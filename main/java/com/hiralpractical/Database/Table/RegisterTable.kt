package com.hiralpractical.Database.Table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "register_table")
class RegisterTable() {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

    @ColumnInfo(name = "Username")
    var name: String = ""

    @ColumnInfo(name = "email")
    var email: String = ""

    @ColumnInfo(name = "phone")
    var phone: String = ""

    @ColumnInfo(name = "profile_pic")
    var profile_pic: String = ""

}