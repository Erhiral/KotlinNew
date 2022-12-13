package com.hiralpractical.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.hiralpractical.Database.Dao.RegisterDao
import com.hiralpractical.Database.Table.RegisterTable

@Database(
    entities = [RegisterTable::class], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun registerDao(): RegisterDao
    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(ctx: Context): AppDatabase {
            if(instance != null) return instance!!
            synchronized(this) {
                instance = databaseBuilder(ctx, AppDatabase::class.java, "REGISTER_DATABASE")
                    .fallbackToDestructiveMigration()
                     .allowMainThreadQueries()
                    .build()

                return instance!!
            }
        }

        fun close(){
            if(instance?.isOpen == true){
                instance?.close()
            }
            instance = null
        }




    }
}