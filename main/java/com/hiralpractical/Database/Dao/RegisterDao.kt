package com.hiralpractical.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hiralpractical.Database.Table.RegisterTable

@Dao
interface RegisterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(registerTable: RegisterTable) : Long

    @Query("select * from register_table order by id")
    fun getAllUserRegisterDetails(): LiveData<List<RegisterTable>>

    @Query("DELETE from register_table")
    fun deleteAllData()

    @Query("DELETE from register_table WHERE id = :id")
    fun getdeleteUser(id:Long)

    @Query("UPDATE register_table SET Username = :name,email= :Email,phone =:phone WHERE id = :tid")
    fun updateUser(tid: Long, name: String?,Email: String?,phone: String?)


}